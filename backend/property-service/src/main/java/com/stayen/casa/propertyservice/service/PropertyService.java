package com.stayen.casa.propertyservice.service;

import java.util.List;

import com.stayen.casa.propertyservice.dto.APIResponse;
import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.dto.PropertyResponse;
import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.dto.UpdatePropertyRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

public interface PropertyService {
	
	List<PropertyEntity> getAllProperty();
	
	APIResponse addNewProperty(PropertyRequest propertyDetails, String ownerId);
	
	PropertyResponse getPropertyById(String propertyId);
	
	PropertyResponse updatePropertyDetails(PropertyRequest updatedDetails,String propertyId);
	
	PropertyResponse updatePropertyAvailabilityById(String propertyId);
	
	PropertyResponse updatePartialProperty(UpdatePropertyRequest updatedFields, String propertyId);
	
	List<PropertyResponse> searchProperties(PropertySearchRequest searchFields);
	
	APIResponse deletePropertyById(String propertyId);

}