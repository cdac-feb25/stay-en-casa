package com.stayen.casa.authenticationservice.constant;

public class ErrorConstant {
	
	/**
	 * Credential Exception
	 */
	public static final String NO_ACCOUNT_FOUND = "We couldn’t find an account matching this email.";
	public static final String SESSION_NOT_FOUND = "No active session found. You are already signed out.";
	public static final String ACCOUNT_ALREADY_EXIST = "Account already exists. Please try logging in or use a different email.";
	public static final String INVALID_CREDENTIAL = "Login failed. The email or password is incorrect.";

	
	/**
	 * Token Exception
	 */
	public static final String EMPTY_TOKEN = "Missing or empty authorization token.";
	public static final String INVALID_TOKEN = "Invalid token. Authentication failed.";
}
