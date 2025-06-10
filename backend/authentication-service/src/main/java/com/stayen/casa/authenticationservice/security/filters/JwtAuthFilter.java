package com.stayen.casa.authenticationservice.security.filters;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stayen.casa.authenticationservice.constant.ErrorConstant;
import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.dto.ErrorResponseDTO;
import com.stayen.casa.authenticationservice.exception.token.EmptyTokenException;
import com.stayen.casa.authenticationservice.exception.token.InvalidTokenException;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.security.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private final String AUTH_HEADER_NAME = "Authorization";
	private final String AUTH_TOKEN_NAME = "Bearer ";
	
	private final JwtUtils jwtUtils;
	
	@Autowired
	public JwtAuthFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("JWT Auth Filter intercepted : " + request.getRequestURI());
		
		String authHeader = request.getHeader(AUTH_HEADER_NAME);
		
		System.out.println("Auth Header : " + authHeader);
		
		/**
		 * Getting Header data from HttpRequest if exists
		 */
		if(authHeader != null && authHeader.startsWith(AUTH_TOKEN_NAME)) {
			String token = authHeader.substring(7);
			
			System.out.println("Auth Token : " + token);
			
			try {
				/**
				 * Verifying JWT and getting payload from it
				 */
				Claims verifiedJwtClaims = jwtUtils.validateToken(token);
				
				/**
				 * Getting Data from Claims
				 */
				JwtModel jwtUserInfo = new JwtModel(verifiedJwtClaims.getSubject(), verifiedJwtClaims.get(TokenConstant.DEVICE_ID, String.class));
				Date expiration = verifiedJwtClaims.getExpiration();
				Date currentTime = new Date(System.currentTimeMillis());
				
				System.out.println("Claims : " + verifiedJwtClaims);
				
				/**
				 * if, 
				 * JWT is Valid and still active (not-expired)
				 * 
				 * Create UsernamePassword object to store verified user data
				 * and to pass it to SecurityContextHolder
				 * 
				 * else,
				 * set the status code to 401 - Unauthorized
				 */
				if(expiration != null && currentTime.before(expiration)) {
					UsernamePasswordAuthenticationToken verifiedUser = new 
							UsernamePasswordAuthenticationToken(jwtUserInfo, null, null);
					
					SecurityContextHolder.getContext().setAuthentication(verifiedUser);
					
				} else {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				}
				
			/**
			 * Throws:
			 * 		UnsupportedJwtException - if the jwt argument does not represent a signed Claims JWT
			 * 		JwtException - if the jwt string cannot be parsed or validated as required.
			 * 		IllegalArgumentException - if the jwt string is null or empty or only whitespace
			 */
				// TODO : Handle Error correctly
			} catch(IllegalArgumentException e) {
//				throw new EmptyTokenException(ErrorConstant.EMPTY_TOKEN);
				System.out.println("Exception Caught : " + e.getMessage());
			} catch (Exception e) { 
//				throw new InvalidTokenException(ErrorConstant.INVALID_TOKEN);
				System.out.println("Exception Caught : " + e.getMessage());
			}
			
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
		
		/**
		 * proceed with further filter chain
		 */
		filterChain.doFilter(request, response);
	}

}
