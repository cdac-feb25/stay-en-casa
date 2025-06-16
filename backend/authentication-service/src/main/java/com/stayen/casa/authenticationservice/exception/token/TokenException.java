package com.stayen.casa.authenticationservice.exception.token;

import com.stayen.casa.authenticationservice.enums.TokenError;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TokenError tokenError;
	
	public TokenException(TokenError tokenError) {
		super(tokenError.getMessage());
		this.tokenError = tokenError;
	}
	
}
