package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum indicating the furnishing status of the property.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Furnishing {
	
	/**
     * Property is partially furnished with essential items like wardrobes, kitchen cabinets, etc.
     */
	SEMI_FURNISHED,
	
	/**
     * Property has no furnishings. Bare shell.
     */
	UNFURNISHED,
	
	/**
     * Property is fully furnished with all required furniture and appliances.
     */
	FURNISHED

}
