package com.stayen.casa.authenticationservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO extends BaseDTO {

	private String password;

	public SignupRequestDTO(String email, String deviceId, String password) {
		super(email, deviceId);
		this.password = password;
	}

	
}
