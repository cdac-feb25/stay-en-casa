package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import com.stayen.casa.authenticationservice.model.JwtModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthErrorDTO {

	private int errorCode;

	private String errorMessage;
	
	private LocalDateTime timestamp;
	
	public AuthErrorDTO(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timestamp = LocalDateTime.now();
	}

}
