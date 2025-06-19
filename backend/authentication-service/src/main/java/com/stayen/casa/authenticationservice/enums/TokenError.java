package com.stayen.casa.authenticationservice.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TokenError {
	
	EMPTY(1001, "Authorization header missing or invalid."),
	
	EXPIRED(1002, "Token has expired. Please log in again to continue."),
	
	BLACKLISTED(1003, "This token is invalid or has already been invalidated."),
	
	INVALID(1004, "Invalid authorization token."),
	
	INVALID_USER(1005, "Invalid user details provided in authorization token."),
    
	MALFORMED(1006, "Malformed authorization. The authorization token format is invalid or corrupted."),
	
	UNSUPPORTED(1007, "The algorithm used in the authorization token is not supported."),
	
	
	BLOCKED(1010, "Access denied. The token is no longer valid. Please log in again to continue. !!!");
	
	/**
	 * 
	 */
	private int code;
	
	private String message;
    
    private TokenError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
