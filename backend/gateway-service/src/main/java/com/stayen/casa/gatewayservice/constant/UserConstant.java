package com.stayen.casa.gatewayservice.constant;

import com.stayen.casa.gatewayservice.enums.TokenError;
import com.stayen.casa.gatewayservice.exception.TokenException;
import com.stayen.casa.gatewayservice.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserConstant {

    public static User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth.getDetails() instanceof User)) {
            throw new TokenException(TokenError.INVALID_USER);
        }

        return (User)auth.getDetails();
    }

}
