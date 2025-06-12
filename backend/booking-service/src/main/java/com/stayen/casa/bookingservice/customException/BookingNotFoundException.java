package com.stayen.casa.bookingservice.customException;

/**
 * Custom exception thrown when a Booking Details are not found in the system.
 */

public class BookingNotFoundException extends RuntimeException {
	
	public BookingNotFoundException(String message)
	{
		super(message);
	}

}
