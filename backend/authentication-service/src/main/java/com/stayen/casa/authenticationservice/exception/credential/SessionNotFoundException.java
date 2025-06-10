package com.stayen.casa.authenticationservice.exception.credential;

public class SessionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionNotFoundException(String message) {
		super(message);
	}
	
}
