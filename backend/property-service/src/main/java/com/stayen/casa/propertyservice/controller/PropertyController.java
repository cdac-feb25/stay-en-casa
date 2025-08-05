package com.stayen.casa.propertyservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.dto.UpdatePropertyRequest;
import com.stayen.casa.propertyservice.service.PropertyService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/*
 * PropertyController handles all HTTP requests related to property operations.
 */

@RestController
@RequestMapping("/api/properties")
@AllArgsConstructor
public class PropertyController {

	// Injecting the PropertyService to handle business logic.
	private final PropertyService propertyService;
	
	/**
	 * Retrieves all listed properties from all registered owners.
	 * This endpoint is typically used by Admin users.
	 *
	 * @return ResponseEntity containing a list of all properties.
	 */
	@GetMapping
	@Operation(description = "Display all Properties")
	public ResponseEntity<?> getAllProperty(){
		
		return ResponseEntity.ok(propertyService.getAllProperty());
	}
	
	/**
	 * Adds a new property under the specified owner.
	 *
	 * @param propertyDetails The property details sent in the request body (validated).
	 * @param ownerId The ID of the owner under whom the property will be listed.
	 * @return ResponseEntity containing a success message or error response.
	 */
	@PostMapping("/{ownerId}")
	@Operation(description = "Add a new Property under the specified Owner.")
	public ResponseEntity<?> addNewProperty(@RequestBody @Valid PropertyRequest propertyDetails, @PathVariable String ownerId)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.addNewProperty(propertyDetails, ownerId));
	}
	
	/**
	 * Updates the images associated with the specified property.
	 * This endpoint replaces the current list of image URLs for the property with the provided list.
	 * 
	 * @param propertyId The ID of the property to update.
	 * @param imageUrls A list of image URLs to associate with the property.
	 * @return ResponseEntity containing a success message or error response.
	 */
	@PutMapping("/{propertyId}/images")
	@Operation(description = "Upload the Image of Property")
	public ResponseEntity<?> updatePropertyImages(@PathVariable String propertyId, @RequestBody List<String> imageUrls)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.updatePropertyImages(propertyId,imageUrls));
	}
	
	/**
	 * Retrieves a single property by its ID.
	 *
	 * @param propertyId The ID of the property to retrieve.
	 * @return ResponseEntity containing the property details.
	 */
	@GetMapping("/{propertyId}")
	@Operation(description = "Display the Property of given Property ID")
	public ResponseEntity<?> getPropertyById(@PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertyById(propertyId));
	}
	
	/**
     * Update all details of an existing property.
     * 
     * @param propertyId The ID of the property to update.
     * @param updatedDetails The full updated property data.
     * @return ResponseEntity containing the updated property details.
     */
	@PutMapping("/{propertyId}")
	@Operation(description = "Update all Details of given Property ID")
	public ResponseEntity<?> updatePropertyById(@RequestBody @Valid PropertyRequest updatedDetails, @PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.updatePropertyDetails(updatedDetails, propertyId));
	}
	
	/**
     * Toggle or Update the Availability status of given property.
     * 
     * @param propertyId The ID of the property whose availability will be changed.
     * @return ResponseEntity containing the updated property details.
     */
	@PatchMapping("/{propertyId}/availability")
	@Operation(description = "Updates Availability of given Property ID")
	public ResponseEntity<?> updatePropertyAvailabilityById(@PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.updatePropertyAvailabilityById(propertyId));
	}
	
	/**
     * Perform a partial update by modifying specific fields of a property.
     * 
     * @param propertyId The ID of the property to update.
     * @param updatedFields The fields and values to update.
     * @return ResponseEntity containing the partially updated property details.
     */
	@PatchMapping("/{propertyId}")
	@Operation(description = "Update Partial Details of Given Property ID")
	public ResponseEntity<?> updatePartialProperty(@RequestBody UpdatePropertyRequest updatedFields, @PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.updatePartialProperty(updatedFields, propertyId));
	}
	
	/**
	 * Searches for properties based on the provided filter criteria.
	 *
	 * @param searchFields The filters provided in the request body, such as price range, category, location, etc.
	 * @return ResponseEntity containing a list of properties that match the given filters.
	 */
	@PostMapping("/search")
	@Operation(description = "Search Properties Based on Given Search Filter(s)")
	public ResponseEntity<?> searchProperties(@RequestBody @Valid PropertySearchRequest searchFields)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.searchProperties(searchFields));
	}
	
	/**
	 * Deletes a property by its ID.
	 *
	 * @param propertyId The ID of the property to be deleted.
	 * @return ResponseEntity containing an API response indicating success or failure.
	 */
	@DeleteMapping("/{propertyId}")
	@Operation(description = "Delete the Property by given Property ID")
	ResponseEntity<?> deletePropertyById(@PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.deletePropertyById(propertyId));
	}
	
	@GetMapping("/owner/{ownerId}")
	@Operation(description = "Get properties added by a specific owner")
	public ResponseEntity<?> getPropertiesByOwner(@PathVariable String ownerId) {
	    return ResponseEntity.ok(propertyService.getPropertiesByOwner(ownerId));
	}
}
