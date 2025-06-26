package com.stayen.casa.propertyservice.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.propertyservice.enums.PropertyError;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for handling all types of exceptions
 * thrown from controllers or services in a centralized way.
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
     * Handles custom PropertyException and maps it to a meaningful response.
     *
     * @param ex the PropertyException thrown
     * @return ResponseEntity with error message and BAD_REQUEST status
     */
	@ExceptionHandler(PropertyException.class)
	public ResponseEntity<?> handlePropertyException(PropertyException ex)
	{
		PropertyError error = ex.getPropertyError();
		
		log.error("Property error occurred: [{}] - {}", error.getCode(), error.getMessage());

		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status","error","message",error.getMessage()));
	}
	
	
	/**
     * Handles any uncaught generic exceptions.
     *
     * @param ex the exception thrown
     * @return ResponseEntity with generic error message and INTERNAL_SERVER_ERROR status
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex)
	{
		
		log.error("Unhandled Exception occurred: {}", ex.getMessage(),ex);
		
		PropertyError error = PropertyError.GENERIC_ERROR;
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status","error","message",error.getMessage()));
		
	}

}
