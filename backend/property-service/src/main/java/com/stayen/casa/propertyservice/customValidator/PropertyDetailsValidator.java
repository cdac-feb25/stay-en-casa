package com.stayen.casa.propertyservice.customValidator;

import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PropertyDetailsValidator implements ConstraintValidator<ValidPropertyDetails, PropertyRequest> {

	@Override
	public boolean isValid(PropertyRequest value, ConstraintValidatorContext context) {
		if(value.getPropertyCategory()==null) return true;
		
		boolean isPlot = value.getPropertyCategory() == PropertyCategory.PLOT;
		
		boolean valid = true;
		
		if(!isPlot) {
			if(value.getBathrooms()==null)
			{
				context.buildConstraintViolationWithTemplate("Please enter the Total Number of Bathrooms").addPropertyNode("bathrooms")
				.addConstraintViolation();
				valid = false;
			}
			
			if(value.getBedrooms()==null)
			{
				context.buildConstraintViolationWithTemplate("Please enter the Total Number of Bedrooms").addPropertyNode("bedrooms")
				.addConstraintViolation();
				valid = false;
			}
			
			if(value.getFloorNumber()==null)
			{
				context.buildConstraintViolationWithTemplate("Please enter the Correct Floor Number").addPropertyNode("floorNumber")
				.addConstraintViolation();
				valid = false;
			}
			
			if(value.getFurnishing()==null)
			{
				context.buildConstraintViolationWithTemplate("Please Jusify the Furnishing (Furnished, Unfurnished, Semi-Furnished)")
				.addPropertyNode("furnishing").addConstraintViolation();
				valid = false;
			}
			
			if(value.getAmenities()==null || value.getAmenities().isEmpty())
			{
				context.buildConstraintViolationWithTemplate("Please enter the List of Amenities available").addPropertyNode("amenities")
				.addConstraintViolation();
				valid = false;
			}
			if(value.getTotalFloors()==null)
			{
				context.buildConstraintViolationWithTemplate("Please Enter the Total Number of Floors").addPropertyNode("totalFloors")
				.addConstraintViolation();
				valid = false;
			}
			if(!valid)
				context.disableDefaultConstraintViolation();
		}
		
		return valid;
	}

}
