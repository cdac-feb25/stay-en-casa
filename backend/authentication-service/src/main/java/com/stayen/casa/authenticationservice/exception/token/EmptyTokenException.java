package com.stayen.casa.authenticationservice.exception.token;

public class EmptyTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyTokenException(String message) {
		super(message);
	}
	
}
