package com.stayen.casa.authenticationservice.security.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.enums.TokenType;
import com.stayen.casa.authenticationservice.model.JwtModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	
	@Value("${jwt-secret-key}")
	private String jwtSecretKey;
	
	/**
	 * <pre>
	 * Create an object of SecretKey class
	 * Based on keyBytes, Algorithm
	 * </pre>
	 * 
	 * @return SecretKey Object
	 */
	@Bean
	private SecretKey getJwtSecretKey() {
		byte[] jwtSecretKeyBytes = jwtSecretKey.getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(jwtSecretKeyBytes, TokenConstant.JWT_KEY_ALGORITHM_NAME);
	}
	
	/**
	 * <pre>
	 * Verify the authenticity of token provided
	 * using the SecretKey
	 * </pre>
	 * 
	 * @param token
	 * @return Claims containing payload
	 */
	public Claims validateToken(String token) {
		return Jwts.parser()
		.verifyWith(getJwtSecretKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
	}
	
	// TODO: used access token is getting used again and again
	public void invalidateToken(String token) {
//		Jwts.
	}
	
	/**
	 * 
	 * 
	 * @param jwtModel
	 * @param tokenType
	 * @return String - JWT token
	 */
	public String generateJwtToken(JwtModel jwtModel, TokenType tokenType) {
		long currentTimeInMillis = System.currentTimeMillis();
		
		long expirationTimeInMillis = currentTimeInMillis + tokenType.getValidity();
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(TokenConstant.EMAIL, jwtModel.getEmail());
		claims.put(TokenConstant.DEVICE_ID, jwtModel.getDeviceId());
		claims.put(TokenConstant.TOKEN_TYPE, tokenType);
		
		return Jwts.builder()
			.subject(jwtModel.getUid())
			.claims(claims)
			.issuedAt(new Date(currentTimeInMillis))
			.expiration(new Date(expirationTimeInMillis))
			.issuer(TokenConstant.TOKEN_ISSUER)
			.signWith(getJwtSecretKey())
			.compact();
	}
	
	
	
	
	
	
	
	
	
//	/**
//	 * <pre>
//	 * Generate a JWT Access Token
//	 * with Payload provided
//	 * with Expiration Time of 15 minutes
//	 * </pre>
//	 * 
//	 * @param jwtModel
//	 * @return new JWT Token
//	 */
//	private String generateAccessToken(JwtModel jwtModel) {
//		long currentTimeInMillis = System.currentTimeMillis();
//		long expirationTimeInMillis = currentTimeInMillis + ACCESS_TOKEN_EXPIRATION_TIME_MILLIS;
//		
//		return Jwts.builder()
//		.subject(jwtModel.getUid())
////		.claim("key", "value")  // for custom claims
//		.claim(TokenConstant.EMAIL, jwtModel.getEmail())
//		.claim(TokenConstant.DEVICE_ID, jwtModel.getDeviceId())
//		.issuedAt(new Date(currentTimeInMillis))
//		.expiration(new Date(expirationTimeInMillis))
//		.issuer(TokenConstant.TOKEN_ISSUER)
//		.signWith(getJwtSecretKey())
//		.compact();
//	}
//	
//	/**
//	 * <pre>
//	 * Generate a JWT Access Token
//	 * with Payload provided
//	 * with Expiration Time of 24 hours
//	 * </pre>
//	 * 
//	 * @param jwtModel
//	 * @return new JWT Token
//	 */
//	private String generateRefreshToken(JwtModel jwtModel) {
//		long currentTimeInMillis = System.currentTimeMillis();
//		long expirationTimeInMillis = currentTimeInMillis + REFRESH_TOKEN_EXPIRATION_TIME_MILLIS;
//		
//		return Jwts.builder()
//		.subject(jwtModel.getUid())
////		.claim("key", "value")  // for custom claims
//		.claim(TokenConstant.EMAIL, jwtModel.getEmail())
//		.claim(TokenConstant.DEVICE_ID, jwtModel.getDeviceId())
//		.issuedAt(new Date(currentTimeInMillis))
//		.expiration(new Date(expirationTimeInMillis))
//		.issuer(TokenConstant.TOKEN_ISSUER)
//		.signWith(getJwtSecretKey())
//		.compact();
//	}
//	
//	private String generateTempToken(JwtModel jwtModel) {
//		long currentTimeInMillis = System.currentTimeMillis();
//		long expirationTimeInMillis = currentTimeInMillis;
//		
//		return Jwts.builder()
//		.subject(jwtModel.getUid())
////		.claim("key", "value")  // for custom claims
//		.claim(TokenConstant.EMAIL, jwtModel.getEmail())
//		.claim(TokenConstant.DEVICE_ID, jwtModel.getDeviceId())
//		.issuedAt(new Date(currentTimeInMillis))
//		.expiration(new Date(expirationTimeInMillis))
//		.issuer(TokenConstant.TOKEN_ISSUER)
//		.signWith(getJwtSecretKey())
//		.compact();
//	}
	
}
