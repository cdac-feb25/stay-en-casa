package com.stayen.casa.userservice.controller.advice;

import com.stayen.casa.userservice.dto.ErrorResponseDTO;
import com.stayen.casa.userservice.exception.GeneralException;
import com.stayen.casa.userservice.exception.ProfileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserProfileControllerAdvice {

    @ExceptionHandler(exception = ProfileException.class)
    public ResponseEntity<?> handleProfileException(ProfileException profileException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(profileException.getError()));
    }

    @ExceptionHandler(exception = GeneralException.class)
    public ResponseEntity<?> handleGeneralException(GeneralException generalException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(generalException.getError()));
    }

}
