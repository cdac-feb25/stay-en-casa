package com.stayen.casa.propertyservice.service;

import java.util.List;

import com.stayen.casa.propertyservice.dto.APIResponse;
import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

public interface PropertyService {
	
	List<PropertyEntity> getAllProperty();
	
	APIResponse addNewProperty(PropertyRequest propertyDetails, String ownerId);

}
