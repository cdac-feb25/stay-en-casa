package com.stayen.casa.authenticationservice.exception.credential;

import com.stayen.casa.authenticationservice.enums.AuthError;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AuthError authError;
	
	public AuthException(AuthError authError) {
		super(authError.getMessage());
		this.authError = authError;
	}
	
}
