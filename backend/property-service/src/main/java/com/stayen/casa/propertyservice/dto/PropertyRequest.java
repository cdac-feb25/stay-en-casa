package com.stayen.casa.propertyservice.dto;

import java.util.List;

import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;
import com.stayen.casa.propertyservice.customValidator.ValidPropertyDetails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO used to create or update property listings.
 */

@ValidPropertyDetails
@Data
public class PropertyRequest {

	@NotBlank(message = "Property Name is Required")
	private String propertyName;
	
	@NotBlank(message = "Property Description is Required")
	private String propertyDescription;
	
	@NotNull(message = "Listing Type is Required")
	private ListingType listingType;
	
	@NotNull(message = "Property Category is Required")
	private PropertyCategory propertyCategory;
	
	@NotNull(message = "Price is Required")
	@Positive(message = "Price must be Greater than 0")
	private Double price;
	
	@NotNull(message = "Area is Required")
	@Positive(message = "Area must be a Positve Number")
	private Double area;
	
	@NotNull(message = "Area Unit is Required")
	private AreaUnit unit;
	
	
	private Integer bedrooms;
	
	private Integer bathrooms;
	
	private Integer floorNumber;
	
	private Integer totalFloors;
	
	private Furnishing furnishing;
	
	private List<String> amenities;
	
	@NotNull(message = "Location is Required")
	@Valid
	private LocationRequest location;
	
	@NotNull(message = "Image is Required")
	private List<@NotNull(message = "Image URL is Required") String> images;
	
	@NotNull(message = "Specification of Availability Status is must")
	private Boolean isAvailable;
}
