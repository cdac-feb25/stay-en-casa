package com.stayen.casa.propertyservice.dto;

import com.stayen.casa.propertyservice.entity.ListingType;
import com.stayen.casa.propertyservice.entity.PropertyCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO used to create or update property listings.
 */

@Data
public class PropertyRequest {

	@NotBlank(message = "Property Name is Required")
	private String propertyName;
	
	@NotBlank(message = "Property Description is Required")
	private String propertyDescription;
	
	@NotBlank(message = "Listing Type is Required")
	private ListingType listingType;
	
	@NotBlank(message = "Property Category is Required")
	private PropertyCategory propertyCategory;
	
	
}
