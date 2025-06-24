package com.stayen.casa.propertyservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stayen.casa.propertyservice.dto.APIResponse;
import com.stayen.casa.propertyservice.dto.LocationUpdateRequest;
import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.dto.PropertyResponse;
import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.dto.UpdatePropertyRequest;
import com.stayen.casa.propertyservice.entity.Location;
import com.stayen.casa.propertyservice.entity.PropertyEntity;
import com.stayen.casa.propertyservice.enums.PropertyError;
import com.stayen.casa.propertyservice.exception.PropertyException;
import com.stayen.casa.propertyservice.repository.PropertyCustomRepository;
import com.stayen.casa.propertyservice.repository.PropertyRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
	
	private final PropertyRepository propertyRepository;
	
	private final ModelMapper modelMapper;
	
	private final PropertyCustomRepository customRepository;

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
		
		//Generate random unique Property ID
		String uuid = "prop-"+UUID.randomUUID();
		
		propertyEntity.setPropertyId(uuid);
		
		//Set Additional Field
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		
		//Save to Database
		propertyRepository.save(propertyEntity);
		
		//5. Return the Listing Confirmation Message
		return new APIResponse("Property is Listed!!!!! Your Property ID: "+propertyEntity.getPropertyId());
	}

	@Override
	public PropertyResponse getPropertyById(String propertyId) {
		
		PropertyEntity propertyEntity =  propertyRepository.findById(propertyId)
				.orElseThrow(()-> new PropertyException(PropertyError.PROPERTY_NOT_EXIST));
		
		return modelMapper.map(propertyEntity, PropertyResponse.class);
	}

	@Override
	public PropertyResponse updatePropertyDetails(PropertyRequest updatedDetails,String propertyId) {
		
		PropertyEntity propertyEntity = propertyRepository.findById(propertyId)
				.orElseThrow(()-> new PropertyException(PropertyError.PROPERTY_NOT_EXIST));
		
		// Map all matching fields from DTO to entity
		modelMapper.map(updatedDetails, propertyEntity);
		
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		
		propertyRepository.save(propertyEntity);
		
		// Map entity to response
		return modelMapper.map(propertyEntity, PropertyResponse.class);
	}

	@Override
	public PropertyResponse updatePropertyAvailabilityById(String propertyId) {
		
		PropertyEntity propertyEntity = propertyRepository.findById(propertyId)
				.orElseThrow(()->new PropertyException(PropertyError.PROPERTY_NOT_EXIST));
		
		//Change the Availability of Property
		propertyEntity.setAvailable(!propertyEntity.isAvailable());
		
		propertyRepository.save(propertyEntity);
		
		return modelMapper.map(propertyEntity,PropertyResponse.class);
	}

	@Override
	public PropertyResponse updatePartialProperty(UpdatePropertyRequest updatedFields, String propertyId) {
		
		PropertyEntity propertyEntity = propertyRepository.findById(propertyId)
				.orElseThrow(()->new PropertyException(PropertyError.PROPERTY_NOT_EXIST));
		
		if(updatedFields.getPropertyName()!=null)
		{
			propertyEntity.setPropertyName(updatedFields.getPropertyName());
		}
		
		if(updatedFields.getPropertyDescription()!=null)
		{			
			propertyEntity.setPropertyDescription(updatedFields.getPropertyDescription());
		}
		
		if(updatedFields.getListingType()!=null)
		{			
			propertyEntity.setListingType(updatedFields.getListingType());
		}
		
		if(updatedFields.getPropertyCategory()!=null)
		{
			propertyEntity.setPropertyCategory(updatedFields.getPropertyCategory());
		}
		
		if(updatedFields.getPrice()!=null)
		{
			propertyEntity.setPrice(updatedFields.getPrice());
		}
		
		if(updatedFields.getArea()!=null)
		{
			propertyEntity.setArea(updatedFields.getArea());
		}
		
		if(updatedFields.getUnit()!=null)
		{
			propertyEntity.setUnit(updatedFields.getUnit());
		}
		
		if(updatedFields.getBedrooms()!=null)
		{
			propertyEntity.setBedrooms(updatedFields.getBedrooms());
		}
		
		if(updatedFields.getBathrooms()!=null)
		{
			propertyEntity.setBathrooms(updatedFields.getBathrooms());
		}
		
		if(updatedFields.getFloorNumber()!=null)
		{
			propertyEntity.setFloorNumber(updatedFields.getFloorNumber());
		}
		
		if(updatedFields.getTotalFloors()!=null)
		{
			propertyEntity.setTotalFloors(updatedFields.getTotalFloors());
		}
		
		if(updatedFields.getFurnishing()!=null)
		{
			propertyEntity.setFurnishing(updatedFields.getFurnishing());
		}
		
		if(updatedFields.getAmenities()!=null)
		{
			propertyEntity.setAmenities(updatedFields.getAmenities());
		}
		
		if(updatedFields.getLocation()!=null)
		{
			if(propertyEntity.getLocation()==null)
			{
				propertyEntity.setLocation(new Location());
			}
			
			updateLocationFields(propertyEntity.getLocation(), updatedFields.getLocation());
		}
		
		if(updatedFields.getImages()!=null)
		{
			propertyEntity.setImages(updatedFields.getImages());
		}
		
		if(updatedFields.getIsAvailable()!=null)
		{			
			propertyEntity.setAvailable(updatedFields.getIsAvailable());
		}
		
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		
		propertyRepository.save(propertyEntity);
		
		return modelMapper.map(propertyEntity, PropertyResponse.class);
	}
	
	public void updateLocationFields(Location existingLocation, LocationUpdateRequest updatedLocations)
	{
		
		
		if(updatedLocations.getLatitude()!=null)
		{
			existingLocation.setLatitude(updatedLocations.getLatitude());
		}
		
		if(updatedLocations.getLongitude()!=null)
		{
			existingLocation.setLongitude(updatedLocations.getLongitude());
		}
		
		if(updatedLocations.getAddress()!=null)
		{
			existingLocation.setAddress(updatedLocations.getAddress());
		}
		
		if(updatedLocations.getLocality()!=null)
		{
			existingLocation.setLocality(updatedLocations.getLocality());
		}
		
		if(updatedLocations.getCity()!=null)
		{
			existingLocation.setCity(updatedLocations.getCity());
		}
		
		if(updatedLocations.getState()!=null)
		{
			existingLocation.setState(updatedLocations.getState());
		}
		
		if(updatedLocations.getCountry()!=null)
		{
			existingLocation.setCountry(updatedLocations.getCountry());
		}
		
		if(updatedLocations.getPincode()!=null)
		{
			existingLocation.setPincode(updatedLocations.getPincode());
		}
		
	}

	@Override
	public List<PropertyResponse> searchProperties(PropertySearchRequest searchFields) {
		
		List<PropertyEntity> properties =  customRepository.searchProperties(searchFields);
		
		return properties.stream().map(p-> modelMapper.map(p, PropertyResponse.class)).collect(Collectors.toList());
	}

	@Override
	public APIResponse deletePropertyById(String propertyId) {
		PropertyEntity property = propertyRepository.findById(propertyId)
				.orElseThrow(()-> new PropertyException(PropertyError.PROPERTY_NOT_EXIST));
		
		propertyRepository.delete(property);
		
		return new APIResponse("Property with ID: "+propertyId+" Deleted Successfully");
	}
	

}
