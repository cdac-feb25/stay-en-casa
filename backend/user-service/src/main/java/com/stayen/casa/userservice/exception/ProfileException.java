package com.stayen.casa.userservice.exception;

import com.stayen.casa.userservice.enums.AuthError;
import lombok.Getter;

@Getter
public class ProfileException extends RuntimeException {

    AuthError error;

    public ProfileException(AuthError error) {
        super(error.getMessage());
        this.error = error;
    }

}
