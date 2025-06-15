package com.stayen.casa.authenticationservice.security.filters;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stayen.casa.authenticationservice.constant.ErrorConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.dto.AuthErrorDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.enums.TokenErrorCode;
import com.stayen.casa.authenticationservice.enums.TokenType;
import com.stayen.casa.authenticationservice.exception.token.EmptyTokenException;
import com.stayen.casa.authenticationservice.exception.token.TokenException;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.model.User;
import com.stayen.casa.authenticationservice.security.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	private static final String CLASS_NAME = JwtAuthFilter.class.getSimpleName();

	private static final String AUTH_HEADER_NAME = "Authorization";
	private static final String AUTH_TOKEN_NAME = "Bearer ";

	private final JwtUtils jwtUtils;
	private final ObjectMapper mapper;

	@Autowired
	public JwtAuthFilter(JwtUtils jwtUtils, ObjectMapper mapper) {
		this.jwtUtils = jwtUtils;
		this.mapper = mapper; 
	}
	
	private void setAuthErrorResponse(HttpServletResponse response, TokenErrorCode tokenError) throws IOException {
		response.setContentType("application/json");
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(mapper.writeValueAsString(new AuthErrorDTO(tokenError.getCode(), tokenError.getMessage())));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestPath = request.getServletPath();
		
		if(TokenConstant.isPathExcluded(requestPath)) {
			filterChain.doFilter(request, response);
			return;
		}

		/**
		 * Getting Header data from HttpRequest if exists
		 */

		try {
			String authHeader = request.getHeader(AUTH_HEADER_NAME);
			System.out.println(CLASS_NAME + " - Auth Header : " + authHeader);
			
			if (authHeader == null) {
				throw new TokenException(TokenErrorCode.EMPTY);
			}
			
			if((authHeader.startsWith(AUTH_TOKEN_NAME)) == false) {
				throw new TokenException(TokenErrorCode.INVALID);
			}
			
			String token = authHeader.substring(7);
			System.out.println(CLASS_NAME + " - Auth Token : " + token);
			
			Claims jwtClaims = jwtUtils.validateToken(token);
			
			TokenType tokenType = TokenType.valueOf(jwtClaims.get(TokenConstant.TOKEN_TYPE).toString());
			/**
			 * If token != ACCESS
			 * 	then check is requested-path is refresh token path
			 * 		if yes, proceed to controller
			 * 		else, throw error
			 */
			if(tokenType != TokenType.ACCESS) {

				/**
				 * If incoming path is NOT a refresh token path
				 * we cannot proceed
				 */
				if(TokenConstant.isRefreshTokenPath(requestPath) == false) {
					throw new TokenException(TokenErrorCode.INVALID);
				}
			} else {
				
				/**
				 * If incoming path is refresh token path,
				 * then we cannot proceed with access token
				 */
				if(TokenConstant.isRefreshTokenPath(requestPath)) {
					throw new TokenException(TokenErrorCode.BLOCKED);
				}
			}

//				if(isBlacklisted(jwtClaims)) {
//					throw new TokenException(TokenErrorCode.BLACKLISTED);
//					setAuthErrorResponse(response, TokenErrorCode.BLACKLISTED);		
//					return;
//				}

			String uid = jwtClaims.getSubject();
			String email = jwtClaims.get(TokenConstant.EMAIL, String.class);
			String deviceId = jwtClaims.get(TokenConstant.DEVICE_ID, String.class);

			User user = new User(uid, email, deviceId, token);

			UsernamePasswordAuthenticationToken verifiedUser = new UsernamePasswordAuthenticationToken(uid, null, null);
			verifiedUser.setDetails(user);

			SecurityContextHolder.getContext().setAuthentication(verifiedUser);

			/**
			 * proceed with further filter chain
			 */
			filterChain.doFilter(request, response);
			
		/**
		 * UnsupportedJwtException - if the jwt argument does not represent a signed Claims JWT
		 * JwtException - if the jwt string cannot be parsed or validated as required.
		 * IllegalArgumentException - if the jwt string is null or empty or only whitespace
		 */
		} catch (TokenException e) {
			setAuthErrorResponse(response, e.getTokenErrorCode());
		} catch (ExpiredJwtException e) {
			setAuthErrorResponse(response, TokenErrorCode.EXPIRED);
		} catch (MalformedJwtException e) {
			setAuthErrorResponse(response, TokenErrorCode.MALFORMED);
		} catch (UnsupportedJwtException e) {
			setAuthErrorResponse(response, TokenErrorCode.UNSUPPORTED);
		} catch (Exception e) {
			System.out.println("Exception class : " + e.getClass().getSimpleName());
			System.out.println(CLASS_NAME + " - Exception Caught : " + e.getMessage());
			setAuthErrorResponse(response, TokenErrorCode.INVALID);
		}
	}

//	private boolean isExpired(Claims jwtClaim) {
//		Date currentTime = new Date(System.currentTimeMillis());
//		Date jwtExpiringTime = jwtClaim.getExpiration();
//
//		return (currentTime.after(jwtExpiringTime));
//	}
//
//	private boolean isInvalidIssuer(Claims jwtClaim) {
//		return !TokenConstant.TOKEN_ISSUER.equals(jwtClaim.getIssuer());
//	}

}
