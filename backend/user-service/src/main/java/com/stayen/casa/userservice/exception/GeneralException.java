package com.stayen.casa.userservice.exception;

import com.stayen.casa.userservice.enums.GenericError;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    GenericError error;

    public GeneralException(GenericError error) {
        super(error.getMessage());
        this.error = error;
    }

}
