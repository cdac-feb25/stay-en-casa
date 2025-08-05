package com.stayen.casa.userservice.controller.advice;

import com.stayen.casa.userservice.dto.ErrorResponseDTO;
import com.stayen.casa.userservice.exception.ProfileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserProfileControllerAdvice {

    @ExceptionHandler(exception = ProfileException.class)
    public ResponseEntity<?> handleProfileException(ProfileException profileException) {

        HttpStatus status = switch (profileException.getError()) {
            case NO_PROFILE_FOUND -> HttpStatus.NOT_FOUND;
            case PROFILE_ALREADY_CREATED -> HttpStatus.CONFLICT;
        };

        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDTO(profileException.getError()));
    }
}
