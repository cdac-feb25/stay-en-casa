package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum representing units used for measuring area.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AreaUnit {
	
	/**
     * Square Meter - commonly used unit in most countries.
     */
	SQ_M,
	
	/**
     * Square Feet - commonly used unit in real estate (especially in the US and India).
     */
	SQ_FT,
	
	/**
     * Square Yard - commonly used in plots and land measurement.
     */
	SQ_YARD,
	
	/**
     * Hectare - used for measuring large land areas, especially in agriculture.
     */
	HECTARE,
	
	/**
     * Acre - used for measuring land, especially in rural or farmland contexts.
     */
	ACRE

}
