package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(exception = RestClientResponseException.class)
    public ResponseEntity<?> handleRestClientResponseException(RestClientResponseException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .headers(ex.getResponseHeaders())
                .body(ex.getResponseBodyAsString());
    }

}
