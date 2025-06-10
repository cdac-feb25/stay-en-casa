package com.stayen.casa.authenticationservice.dto;

/**
 * <h1>This is a Marker Interface</h1>
 * <pre>
 * Help to user LoginRequestDTO and LogoutDTO 
 * for UserCredentialServiceImpl.verifyUserCredential Methods
 * </pre>
 */
public interface LoginLogoutDTO {

	String getEmail();
	
	String getDeviceId();
	
}
