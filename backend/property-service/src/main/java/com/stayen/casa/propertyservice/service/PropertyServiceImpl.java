package com.stayen.casa.propertyservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stayen.casa.propertyservice.dto.APIResponse;
import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;
import com.stayen.casa.propertyservice.enums.PropertyError;
import com.stayen.casa.propertyservice.exception.PropertyException;
import com.stayen.casa.propertyservice.repository.PropertyRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
	
	private final PropertyRepository propertyRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<PropertyEntity> getAllProperty() {
		
		return propertyRepository.findAll();
	}

	@Override
	public APIResponse addNewProperty(PropertyRequest propertyDetails, String ownerID) {
		
		//Check if the property is already Listed
		Optional<PropertyEntity> existingProperty = propertyRepository.findByOwnerIdAndLocationAddressAndLocationPincode(ownerID,
				propertyDetails.getLocation().getAddress(), propertyDetails.getLocation().getPincode());
		
		if(existingProperty.isPresent()) {
			throw new PropertyException(PropertyError.PROPERTY_ALREADY_EXIST);
		}
		
		//Map the Property Details to entity
		PropertyEntity propertyEntity = modelMapper.map(propertyDetails, PropertyEntity.class);
		
		//Set Additional Field
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		
		//Save to Database
		propertyRepository.save(propertyEntity);
		
		//5. Return the Listing Confirmation Message
		return new APIResponse("Property is Listed!!!!! Your Property ID: "+propertyEntity.getPropertyId());
	}

}
