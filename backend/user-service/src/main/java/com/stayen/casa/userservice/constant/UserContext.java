package com.stayen.casa.userservice.constant;

import com.stayen.casa.userservice.enums.AuthError;
import com.stayen.casa.userservice.exception.GeneralException;
import com.stayen.casa.userservice.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {
    private static final String CLASS_NAME = UserContext.class.getSimpleName();

    public static User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || auth.getDetails() == null) {
            throw new GeneralException(AuthError.NO_ACCOUNT_FOUND);
        }

        System.out.println(CLASS_NAME + " - User : " + auth.getPrincipal());
        return (User)auth.getDetails();
    }

}
