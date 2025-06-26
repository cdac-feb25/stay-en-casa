package com.stayen.casa.propertyservice.service;

import java.util.List;

import com.stayen.casa.propertyservice.dto.APIResponse;
import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.dto.PropertyResponse;
import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.dto.UpdatePropertyRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

/**
 * PropertyService defines all the business operations related to property management,
 * including CRUD operations, availability toggling, and filtered search.
 */

public interface PropertyService {
	
	/**
     * Retrieves a list of all properties.
     * @return List of all properties stored in the system.
     */
	List<PropertyEntity> getAllProperty();
	
	/**
     * Adds a new property under the specified owner.
     * @param propertyDetails The request body containing property information.
     * @param ownerId The unique ID of the owner adding the property.
     * @return APIResponse indicating success or failure.
     */
	APIResponse addNewProperty(PropertyRequest propertyDetails, String ownerId);
	
	/**
     * Fetches a specific property using its ID.
     * @param propertyId The unique identifier of the property.
     * @return PropertyResponse containing the property details.
     */
	PropertyResponse getPropertyById(String propertyId);
	
	/**
     * Updates the full property details using the given ID.
     * @param updatedDetails The updated property information.
     * @param propertyId The unique ID of the property to update.
     * @return PropertyResponse containing updated property information.
     */
	PropertyResponse updatePropertyDetails(PropertyRequest updatedDetails,String propertyId);
	
	/**
     * Toggles or updates the availability status of a property.
     * @param propertyId The ID of the property whose availability is being changed.
     * @return PropertyResponse with the updated availability status.
     */
	PropertyResponse updatePropertyAvailabilityById(String propertyId);
	
	/**
     * Updates specific fields of a property (partial update).
     * @param updatedFields The fields to be updated.
     * @param propertyId The ID of the property to update.
     * @return PropertyResponse with the updated fields.
     */
	PropertyResponse updatePartialProperty(UpdatePropertyRequest updatedFields, String propertyId);
	
	/**
     * Searches properties based on dynamic filters like price, category, location, etc.
     * @param searchFields Filters to apply during the search.
     * @return List of properties matching the criteria.
     */
	List<PropertyResponse> searchProperties(PropertySearchRequest searchFields);
	
	 /**
     * Deletes a property by its ID.
     * @param propertyId The unique ID of the property to delete.
     * @return APIResponse indicating whether deletion was successful.
     */
	APIResponse deletePropertyById(String propertyId);

}