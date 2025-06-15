package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.exception.credential.AccountAlreadyExistException;
import com.stayen.casa.authenticationservice.exception.credential.InvalidCredentialException;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;
import com.stayen.casa.authenticationservice.exception.credential.NoActiveSessionFoundException;

@RestControllerAdvice
public class AuthControllerAdvice {
	
}
