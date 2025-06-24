package com.stayen.casa.propertyservice.dto;

import java.util.List;

import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePropertyRequest {
	
	private String propertyName;
	
	private String propertyDescription;
	
	private ListingType listingType;
	
	private PropertyCategory propertyCategory;
	
	private Double price;
	
	private Double area;
	
	private AreaUnit unit;
	
	private Integer bedrooms;
	
	private Integer bathrooms;
	
	private Integer floorNumber;
	
	private Integer totalFloors;
	
	private Furnishing furnishing;
	
	private List<String> amenities;
	
	private LocationUpdateRequest location;
	
	private List<String> images;
	
	private Boolean isAvailable;

}
