package com.stayen.casa.propertyservice.customValidator;

import com.stayen.casa.propertyservice.dto.PropertySearchRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<ValidRange, PropertySearchRequest> {

	@Override
	public boolean isValid(PropertySearchRequest value, ConstraintValidatorContext context) {
		if(value.getMinArea() != null && value.getMaxArea() != null && value.getMinArea()>value.getMaxArea())
			return false;
		if(value.getMinPrice() != null && value.getMaxPrice() != null && value.getMinPrice().compareTo(value.getMaxPrice())>0)
			return false;
		return true;
	}

}
