package com.stayen.casa.authenticationservice.exception.token;

import com.stayen.casa.authenticationservice.enums.TokenErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TokenErrorCode tokenErrorCode;
	
	public TokenException(TokenErrorCode tokenErrorCode) {
		super(tokenErrorCode.getMessage());
		this.tokenErrorCode = tokenErrorCode;
	}
	
}
