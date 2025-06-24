package com.stayen.casa.propertyservice.enums;

import lombok.Getter;

@Getter
public enum PropertyError {
	
	PROPERTY_ALREADY_EXIST(1601, "Property Already Listed, Please try Listing different Property"),
	
	PROPERTY_NOT_EXIST(1602, "The requested property does not exist. Please verify the Property ID and try again.");
	
	private int code;
	
	private String message;

	private PropertyError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	

}
