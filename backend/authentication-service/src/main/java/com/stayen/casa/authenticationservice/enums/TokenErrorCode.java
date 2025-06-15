package com.stayen.casa.authenticationservice.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TokenErrorCode {
	
	EMPTY(110, "Authorization header missing or invalid !!!"),
	EXPIRED(112, "Token has expired. Please log in again to continue. !!!"),
	BLACKLISTED(113, "Token already used or invalidated !!!"),
	INVALID(114, "Invalid Token !!!"),
    MALFORMED(115, "Invalid token format !!!"),
	UNSUPPORTED(116, "Token algorithm is invalid !!!"),
	
	BLOCKED(121, "Access denied. The token is no longer valid. Please log in again to continue. !!!");
	
	/**
	 * 
	 */
	private int code;
	
	private String message;
    
    private TokenErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
