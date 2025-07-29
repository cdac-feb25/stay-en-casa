package com.stayen.casa.gatewayservice.dto;

import com.stayen.casa.gatewayservice.enums.TokenError;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {

    private int errorCode;

    private String errorMessage;

    private LocalDateTime timestamp;

    public ErrorResponseDTO(TokenError tokenError) {
        this.errorCode = tokenError.getCode();
        this.errorMessage = tokenError.getMessage();
        this.timestamp = LocalDateTime.now();
    }

}