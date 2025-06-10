package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.ErrorResponseDTO;
import com.stayen.casa.authenticationservice.exception.token.EmptyTokenException;
import com.stayen.casa.authenticationservice.exception.token.InvalidTokenException;

@RestControllerAdvice
public class TokenControllerAdvice {
	
	@ExceptionHandler(EmptyTokenException.class)
	public ResponseEntity<?> handleInvalidTokenException(EmptyTokenException emptyTokenException) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponseDTO(emptyTokenException.getMessage()));
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException invalidTokenException) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponseDTO(invalidTokenException.getMessage()));
	}
	
}
