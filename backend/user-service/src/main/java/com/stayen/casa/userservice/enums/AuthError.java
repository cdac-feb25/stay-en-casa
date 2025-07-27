package com.stayen.casa.userservice.enums;

import lombok.Getter;

@Getter
public enum AuthError implements GenericError {

    NO_ACCOUNT_FOUND(1201, "We couldn’t find an account matching the given user id."),

//    SESSION_NOT_FOUND(1202, "No active session found. Please login to continue."),

    ACCOUNT_ALREADY_EXIST(1203, "Account already exists. Please try logging in or use a different email.");

//    INVALID_CREDENTIAL(1204, "Login failed. The email or password is incorrect.");

//    INVALID_USER(1205, "");

    /**
     *
     */
    private int code;

    private String message;

    private AuthError(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
