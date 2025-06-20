package com.stayen.casa.propertyservice.dto;

import java.math.BigDecimal;
import java.util.List;

import com.stayen.casa.propertyservice.customValidator.ValidRange;
import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO to capture property search filters provided by users.
 * Allows filtering by location, price, area, bedrooms, and more.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidRange
public class PropertySearchRequest {
	
	//Basic Property Details
	private String title;

	private ListingType listingType;		//RENT OR SALE
	
	private Furnishing furnishing;			//FURNISHED, UNFURNISHED, etc.
	
	private PropertyCategory category;		//FLAT, PLOT, VILLA etc.
	
	//Price Range
	@Min(value = 0,message = "Minimum Price must be Positive")
	private BigDecimal minPrice;			
	
	@Min(value = 0,message = "Maximum Price must be Positive")
	private BigDecimal maxPrice;
	
	//Area Filters (in given Unit)
	@Positive(message = "Minimum Area must be Positive")
	private Double minArea;
	
	@Positive(message = "Maximum Area must be Positive")
	private Double maxArea;
	
	private AreaUnit unit;				//SQ_FT., SQ_M., SQ.YARD, etc.
	
	@Min(value = 0, message = "Number of Bedrooms must be Zero or more")
	private Integer bedrooms;
	
	@Min(value = 0, message = "Number of Bathrooms must be Zero or more")
	private Integer bathrooms;
	
	//Optional Amenities (e.g., Parking, Lift)
	private List<String> amenities;
	
	@Min(value = 0, message = "Minimum Floors must be Zero or more")
	private Integer totalFloors;
	
	//Location Filters (User can search by any or all of these)
	private String city;

    private String state;

    private String locality;

}
