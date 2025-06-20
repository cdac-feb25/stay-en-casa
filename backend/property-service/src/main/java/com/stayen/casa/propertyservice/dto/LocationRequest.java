package com.stayen.casa.propertyservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for property location details.
 */

@Data
public class LocationRequest {
	
	private Double latitude;
	
	private Double longitude;
	
	@NotBlank(message = "Address is Required")
	private String address;
	
	@NotBlank(message = "Locality is Required")
	private String locality;
	
	@NotBlank(message = "City Name is Required")
	private String city;
	
	@NotBlank(message = "State is Required")
	private String state;
	
	@NotBlank(message = "Country is Required")
	private String country;
	
	@NotNull(message = "Pincode is Required")
	@Min(value = 100000, message = "Pincode must be a 6-digit Number")
	@Max(value = 999999, message = "Pincode must be a 6-digit Number")
	private Integer pincode;
	
	
}
