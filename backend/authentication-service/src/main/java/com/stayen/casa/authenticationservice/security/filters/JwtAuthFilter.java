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
import com.stayen.casa.authenticationservice.enums.TokenError;
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
import io.jsonwebtoken.security.SignatureException;
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
	
	private static final List<String> EXCLUDED_PATH = List.of(
		"/auth/login", 
		"/auth/register"
	);
	
	private static final List<String> ONLY_REFRESH_TOKEN_PATH = List.of(
		"/auth/token/refresh"
	);
	
	/**
	 * Verify whether to apply JWT filter
	 * on the request
	 * 
	 * @param incomingPath
	 * @return
	 */
	private static boolean isPathExcluded(String incomingPath) {
		return EXCLUDED_PATH.stream().anyMatch((path) -> path.equalsIgnoreCase(incomingPath));
	}
	
	/**
	 * 
	 * 
	 * @param incomingPath
	 * @return
	 */
	private static boolean isRefreshTokenPath(String incomingPath) {
		return ONLY_REFRESH_TOKEN_PATH.stream().anyMatch((path) -> path.equalsIgnoreCase(incomingPath));
	}
	
	
	/**
	 * Setting the response header, status, and message to return
	 * only when authentication failed.
	 */
	private void setAuthErrorResponse(HttpServletResponse response, TokenError tokenError) throws IOException {
		response.setContentType("application/json");
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(mapper.writeValueAsString(new AuthErrorDTO(tokenError.getCode(), tokenError.getMessage())));
	}
	
	/**
	 * Verifying the incoming request 
	 * whether to apply JWT Filter on those request or not.
	 * 
	 * Here we check if the end-point they wanted to access can be skipped or not
	 * 
	 * @return true if the request need not to be authenticated, else false
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if(isPathExcluded(request.getServletPath())) {
			System.out.println(CLASS_NAME + " - Skipped Incoming Path : " + request.getServletPath());
			return true;
		}
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String incomingRequestPath = request.getServletPath().trim();
		System.out.println(CLASS_NAME + " - Verifying Incoming Path : " + request.getServletPath());
		
		/**
		 * Getting Header data from HttpRequest if exists
		 */

		try {
			String authHeader = request.getHeader(AUTH_HEADER_NAME);
			System.out.println(CLASS_NAME + " - Auth Header : " + authHeader);
			
			if (authHeader == null) {
				throw new TokenException(TokenError.EMPTY);
			}
			
			if((authHeader.startsWith(AUTH_TOKEN_NAME)) == false) {
				throw new TokenException(TokenError.INVALID);
			}
			
			String token = authHeader.substring(7);
			System.out.println(CLASS_NAME + " - Auth Token : " + token);
			
			Claims jwtClaims = jwtUtils.validateToken(token);
			
			TokenType tokenType = TokenType.valueOf(jwtClaims.get(TokenConstant.TOKEN_TYPE).toString());
			System.out.println(CLASS_NAME + " - Token Type : " + tokenType);
			
			/**
			 * ACCESS token cannot be used to refresh tokens, 
			 * even if it is valid JWT
			 */
			if(tokenType == TokenType.TEMP || 
					tokenType == TokenType.ACCESS && isRefreshTokenPath(incomingRequestPath)) {
				System.out.println(CLASS_NAME + " - BLOCKED -- Using ACCESS token for refreshing");
				throw new TokenException(TokenError.BLOCKED);
			}
			
			
			/**
			 * REFRESH token is only allowed for
			 * /auth/token/refresh, or specified in ONLY_REFRESH_TOKEN_PATH 
			 * And 
			 * All the other request path should not be allowed
			 */
			if(tokenType == TokenType.REFRESH && isRefreshTokenPath(incomingRequestPath) == false) {
				System.out.println(CLASS_NAME + " - INVALID -- Using REFRESH token for general purpose");
				throw new TokenException(TokenError.INVALID);
			}
			
			
//			if(tokenType != TokenType.ACCESS) {
//
//				/**
//				 * If incoming path is NOT a refresh token path
//				 * we cannot proceed
//				 */
//				if(isRefreshTokenPath(requestPath) == false) {
//					throw new TokenException(TokenError.INVALID);
//				}
//			} else {
//				
//				/**
//				 * If incoming path is refresh token path,
//				 * then we cannot proceed with access token
//				 */
//				if(isRefreshTokenPath(requestPath)) {
//					throw new TokenException(TokenError.BLOCKED);
//				}
//			}

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
			setAuthErrorResponse(response, e.getTokenError());
		} catch (ExpiredJwtException e) {
			setAuthErrorResponse(response, TokenError.EXPIRED);
		}  catch (SignatureException e) {
			setAuthErrorResponse(response, TokenError.MALFORMED);
		} catch (MalformedJwtException e) {
			setAuthErrorResponse(response, TokenError.MALFORMED);
		} catch (UnsupportedJwtException e) {
			setAuthErrorResponse(response, TokenError.UNSUPPORTED);
		} catch (Exception e) {
			System.out.println("Exception class : " + e.getClass());
			System.out.println(CLASS_NAME + " - Exception Caught : " + e.getMessage());
			setAuthErrorResponse(response, TokenError.INVALID);
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
