package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum indicating the furnishing status of the property.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Furnishing {
	
	SEMI_FURNISHED,
	UNFURNISHED,
	FURNISHED

}
