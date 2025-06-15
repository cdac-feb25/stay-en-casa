package com.stayen.casa.authenticationservice.constant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.stayen.casa.authenticationservice.model.User;

public class UserConstant {
	public static User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getDetails() instanceof User) {
			return (User)auth.getDetails();
		}
		
		return null;
	}
}
