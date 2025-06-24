package com.stayen.casa.propertyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationUpdateRequest {

	private Double latitude;
	
	private Double longitude;
	
	private String address;
	
	private String locality;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Integer pincode;
	
}
