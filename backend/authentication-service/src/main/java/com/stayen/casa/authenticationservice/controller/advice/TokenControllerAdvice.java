package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.exception.token.EmptyTokenException;
import com.stayen.casa.authenticationservice.exception.token.TokenException;

@RestControllerAdvice
public class TokenControllerAdvice {
	
	@ExceptionHandler(EmptyTokenException.class)
	public ResponseEntity<?> handleInvalidTokenException(EmptyTokenException emptyTokenException) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new SimpleResponseDTO(emptyTokenException.getMessage()));
	}

	@ExceptionHandler(TokenException.class)
	public ResponseEntity<?> handleTokenException(TokenException tokenException) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
//		if(auth.isAuthenticated() && auth.)
		
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new SimpleResponseDTO(tokenException.getMessage()));
	}
	
}
