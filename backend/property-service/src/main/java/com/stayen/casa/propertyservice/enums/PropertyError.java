package com.stayen.casa.propertyservice.enums;

import lombok.Getter;

/**
 * Enum representing various error codes and messages related to property operations.
 * Used to standardize error handling and provide meaningful error responses.
 */

@Getter
public enum PropertyError {
	
	PROPERTY_ALREADY_EXIST(1601, "Property Already Listed, Please try Listing different Property"),
	
	PROPERTY_NOT_EXIST(1602, "The requested property does not exist. Please verify the Property ID and try again."),
	
	PROPERTY_CREATION_FAILED(1603, "Failed to create the property. Please try again."),
	
	PROPERTY_UPDATE_FAILED(1604, "Failed to update the property. Please check the input."),
	
	PROPERTY_DELETION_FAILED(1605, "Failed to delete the property. Please verify the Property ID."),
	
	PROPERTY_SEARCH_FAILED(1606, "Property search failed due to invalid filters or internal error."),
	
	GENERIC_ERROR(1699,"An Unknown Error Occurred. Please try again later....");
	
	/**
     * Error code used to identify the type of error.
     */
	private int code;
	
	/**
     * Human-readable error message describing the issue.
     */
	private String message;

	private PropertyError(int code, String message) {
		this.code = code;
		this.message = message;
	}

}
