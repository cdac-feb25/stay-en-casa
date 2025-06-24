package com.stayen.casa.propertyservice.controller;

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


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/properties")
@AllArgsConstructor
public class PropertyController {

	private final PropertyService propertyService;
	
	@GetMapping
	public ResponseEntity<?> getAllProperty(){
		
		return ResponseEntity.ok(propertyService.getAllProperty());
	}
	
	@PostMapping("/{ownerId}")
	public ResponseEntity<?> addNewProperty(@RequestBody @Valid PropertyRequest propertyDetails, @PathVariable String ownerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.addNewProperty(propertyDetails, ownerId));
	}
	
	@GetMapping("/{propertyId}")
	public ResponseEntity<?> getPropertyById(@PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertyById(propertyId));
	}
	
	@PutMapping("/{propertyId}")
	public ResponseEntity<?> updatePropertyById(@RequestBody @Valid PropertyRequest updatedDetails, @PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.updatePropertyDetails(updatedDetails, propertyId));
	}
	
	@PatchMapping("/{propertyId}")
	public ResponseEntity<?> updatePropertyAvailabilityById(@PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.updatePropertyAvailabilityById(propertyId));
	}
	
	@PatchMapping("/{propertyId}")
	public ResponseEntity<?> updatePartialProperty(@RequestBody UpdatePropertyRequest updatedFields, @PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.updatePartialProperty(updatedFields, propertyId));
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchProperties(@RequestBody PropertySearchRequest searchFields)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.searchProperties(searchFields));
	}
	
	@DeleteMapping("/{propertyId}")
	ResponseEntity<?> deletePropertyById(@PathVariable String propertyId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(propertyService.deletePropertyById(propertyId));
	}
}
