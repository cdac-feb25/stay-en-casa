package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.AuthErrorDTO;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.exception.credential.AuthException;

@RestControllerAdvice
public class AuthControllerAdvice {
	
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<?> handleAuthException(AuthException authException) {
		AuthError authError = authException.getAuthError();
		
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new AuthErrorDTO(authError.getCode(), authError.getMessage()));
	}
	
}
