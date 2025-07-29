package com.stayen.casa.userservice.enums;

import lombok.Getter;

@Getter
public enum ProfileError implements GenericError {

    NO_PROFILE_FOUND(1301, "We could not find a profile matching the given user id. Please try to log in and create a profile."),

    PROFILE_ALREADY_CREATED(1302, "Profile already created. If you like to make changes, please update your existing profile.");

    /**
     *
     */
    private int code;

    private String message;

    private ProfileError(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
