package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum representing the type of property listing.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ListingType {

	RENT,
	SALE
}
