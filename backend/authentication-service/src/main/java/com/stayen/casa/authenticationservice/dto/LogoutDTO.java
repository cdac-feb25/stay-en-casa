package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

public class LogoutDTO extends BaseDTO implements LoginLogoutDTO {

	public LogoutDTO(String email, String deviceId) {
		super(email, deviceId);
	}
	
	public LogoutDTO fromUpdatedTimestamp() {
		LocalDateTime currTimestamp = LocalDateTime.now();
		
		this.updateTimestamp(currTimestamp, currTimestamp);
		
		return this;
	}
	
}
