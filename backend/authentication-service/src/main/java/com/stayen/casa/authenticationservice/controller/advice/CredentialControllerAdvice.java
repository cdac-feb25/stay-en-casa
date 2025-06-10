package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.ErrorResponseDTO;
import com.stayen.casa.authenticationservice.exception.credential.AccountAlreadyExistException;
import com.stayen.casa.authenticationservice.exception.credential.InvalidCredentialException;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;
import com.stayen.casa.authenticationservice.exception.credential.SessionNotFoundException;

@RestControllerAdvice
public class CredentialControllerAdvice {
	
	@ExceptionHandler(exception = SessionNotFoundException.class)
	public ResponseEntity<?> handleSessionNotFoundException(SessionNotFoundException sessionNotFoundException) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponseDTO(sessionNotFoundException.getMessage()));
	}

	@ExceptionHandler(exception = NoAccountFoundException.class)
	public ResponseEntity<?> handleNoAccountFoundException(NoAccountFoundException noAccountFoundException) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponseDTO(noAccountFoundException.getMessage()));
	}

	@ExceptionHandler(exception = AccountAlreadyExistException.class)
	public ResponseEntity<?> handleUserAlredyExistsException(AccountAlreadyExistException accountAlreadyExistException) {
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new ErrorResponseDTO(accountAlreadyExistException.getMessage()));
	}
	
	@ExceptionHandler(exception = InvalidCredentialException.class)
	public ResponseEntity<?> handleInvalidCredentialException(InvalidCredentialException invalidCredentialException) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponseDTO(invalidCredentialException.getMessage()));
	}
	
}
