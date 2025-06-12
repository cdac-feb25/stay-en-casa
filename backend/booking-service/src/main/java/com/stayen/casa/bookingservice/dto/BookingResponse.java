package com.stayen.casa.bookingservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.stayen.casa.bookingservice.entity.BookingStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Data Transfer Object representing the response after a booking is created or retrieved.
 * Contains all relevant details to be sent to the client side.
 */

@Getter
@Setter
@ToString
public class BookingResponse {

	private String bookingId;
	
	private String buyerId;
	
	private String propertyId;
	
	private LocalDate bookingDate;
	
	private BookingStatus status;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
