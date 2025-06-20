package com.stayen.casa.propertyservice.enums;

import lombok.Getter;

@Getter
public enum PropertyError {
	
	PROPERTY_ALREADY_EXIST(1601, "Property Already Listed, Please try Listing different Property");
	
	private int code;
	
	private String message;

	private PropertyError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	

}
