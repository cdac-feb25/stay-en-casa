package com.stayen.casa.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDTO extends BaseDTO implements LoginLogoutDTO {
	
	private String password;

	public LoginRequestDTO(String email, String deviceId, String password) {
		super(email, deviceId);
		this.password = password;
	}
	
}
