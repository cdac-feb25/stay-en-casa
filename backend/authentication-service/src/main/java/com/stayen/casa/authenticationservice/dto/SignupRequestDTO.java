package com.stayen.casa.authenticationservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignupRequestDTO extends BaseTimestampDTO {
	
	private String email;
	
	private String password;
	
	private String deviceId;

	public SignupRequestDTO(String email, String deviceId, String password) {
		this.email = email;
		this.password = password;
		this.deviceId = deviceId;
	}

	
}
