package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
	
	private LocalDateTime timestamp;
	
	private String message;

	public ErrorResponseDTO(String message) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
	}
	
}
