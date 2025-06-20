package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum for categorizing the type of property.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PropertyCategory {
	
	FLAT,
	VILLA,
	PLOT,
	COMMERCIAL

}
