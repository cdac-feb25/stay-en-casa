package com.stayen.casa.propertyservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stayen.casa.propertyservice.dto.PropertyRequest;
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
	public ResponseEntity<?> addNewProperty(@RequestBody @Valid PropertyRequest propertyDetails, @PathVariable String ownerId){
		
		System.out.println("Deserialized DTO: " + propertyDetails);

		return ResponseEntity.status(HttpStatus.OK).body(propertyService.addNewProperty(propertyDetails, ownerId));
	}
}
