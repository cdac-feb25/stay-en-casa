package com.stayen.casa.bookingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStatusRequest {

	@NotNull(message = "Status cannot be null")
    private String status;
}
