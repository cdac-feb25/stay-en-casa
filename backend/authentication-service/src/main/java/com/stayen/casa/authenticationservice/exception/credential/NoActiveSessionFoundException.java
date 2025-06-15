package com.stayen.casa.authenticationservice.exception.credential;

public class NoActiveSessionFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoActiveSessionFoundException(String message) {
		super(message);
	}
	
}
