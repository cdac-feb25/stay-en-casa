package com.stayen.casa.propertyservice.repository;

import java.util.List;

import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

public interface PropertyCustomRepository {
	
	List<PropertyEntity> searchProperties(PropertySearchRequest searchFields);

}
