package com.stayen.casa.authenticationservice.enums;

import lombok.Getter;

@Getter
public enum AuthError {
	
//	public static final String NO_ACCOUNT_FOUND = "We couldn’t find an account matching this email.";
//	public static final String SESSION_NOT_FOUND = "No active session found. You are already signed out.";
//	public static final String ACCOUNT_ALREADY_EXIST = "Account already exists. Please try logging in or use a different email.";
//	public static final String INVALID_CREDENTIAL = "Login failed. The email or password is incorrect.";
	
	NO_ACCOUNT_FOUND(1201, "We couldn’t find an account matching this email."),
	
	SESSION_NOT_FOUND(1202, "No active session found. Please login to continue."),
	
	ACCOUNT_ALREADY_EXIST(1203, "Account already exists. Please try logging in or use a different email."),
	
	INVALID_CREDENTIAL(1204, "Login failed. The email or password is incorrect.");

	/**
	 * 
	 */
	private int code;
	
	private String message;
    
    private AuthError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
