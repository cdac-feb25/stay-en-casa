package com.stayen.casa.propertyservice.exception;

import com.stayen.casa.propertyservice.enums.PropertyError;

import lombok.Getter;

@Getter
public class PropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private PropertyError propertyError;

	public PropertyException(PropertyError propertyError) {
		super(propertyError.getMessage());
		this.propertyError = propertyError;
	}
	
	

}
