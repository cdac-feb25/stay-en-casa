package com.stayen.casa.authenticationservice.security.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.model.JwtModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	
	private final String JWT_KEY_ALGORITHM_NAME = "HmacSHA256";
	private final long ACCESS_TOKEN_EXPIRATION_TIME_MILLIS = 1000 * 60 * 15;  // 30 minutes
	private final long REFRESH_TOKEN_EXPIRATION_TIME_MILLIS = 1000 * 60 * 60 * 24;  // 24 hours
	
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
		return new SecretKeySpec(jwtSecretKeyBytes, JWT_KEY_ALGORITHM_NAME);
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
	
	/**
	 * <pre>
	 * Generate a JWT Access Token
	 * with Payload provided
	 * with Expiration Time of 15 minutes
	 * </pre>
	 * 
	 * @param jwtModel
	 * @return new JWT Token
	 */
	public String generateAccessToken(JwtModel jwtModel) {
		long currentTimeInMillis = System.currentTimeMillis();
		long expirationTimeInMillis = currentTimeInMillis + ACCESS_TOKEN_EXPIRATION_TIME_MILLIS;
		
		return Jwts.builder()
		.subject(jwtModel.getEmail())
//		.claim("key", "value")  // for custom claims
		.claim(TokenConstant.DEVICE_ID, jwtModel.getDeviceId())
		.issuedAt(new Date(currentTimeInMillis))
		.expiration(new Date(expirationTimeInMillis))
		.issuer(TokenConstant.TOKEN_ISSUER)
		.signWith(getJwtSecretKey())
		.compact();
	}
	
	/**
	 * <pre>
	 * Generate a JWT Access Token
	 * with Payload provided
	 * with Expiration Time of 24 hours
	 * </pre>
	 * 
	 * @param jwtModel
	 * @return new JWT Token
	 */
	public String generateRefreshToken(JwtModel jwtModel) {
		long currentTimeInMillis = System.currentTimeMillis();
		long expirationTimeInMillis = currentTimeInMillis + REFRESH_TOKEN_EXPIRATION_TIME_MILLIS;
		
		return Jwts.builder()
		.subject(jwtModel.getEmail())
//		.claim("key", "value")  // for custom claims
		.claim(TokenConstant.DEVICE_ID, jwtModel.getDeviceId())
		.issuedAt(new Date(currentTimeInMillis))
		.expiration(new Date(expirationTimeInMillis))
		.issuer(TokenConstant.TOKEN_ISSUER)
		.signWith(getJwtSecretKey())
		.compact();
	}
	
}
