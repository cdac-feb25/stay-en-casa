package com.stayen.casa.authenticationservice.constant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.stayen.casa.authenticationservice.enums.TokenError;
import com.stayen.casa.authenticationservice.exception.token.TokenException;
import com.stayen.casa.authenticationservice.model.User;

public class UserConstant {
	public static User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getDetails() instanceof User) {
			return (User)auth.getDetails();
		}
		
		throw new TokenException(TokenError.INVALID_USER);
	}
}
