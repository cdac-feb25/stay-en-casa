package com.stayen.casa.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtModel {
	
	private String email;
	
	private String deviceId;
	
//	private String username;
//	private String roles  // not required now

}
