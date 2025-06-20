package com.stayen.casa.propertyservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
	
	 	@Schema(description = "Latitude of the property location", example = "18.5204")
	    private Double latitude;

	    @Schema(description = "Longitude of the property location", example = "73.8567")
	    private Double longitude;

	    @Schema(description = "Full address of the property", example = "123 MG Road, Near Central Mall")
	    private String address;

	    @Schema(description = "Locality or neighborhood of the property", example = "Akurdi")
	    private String locality;

	    @Schema(description = "City where the property is located", example = "Pune")
	    private String city;

	    @Schema(description = "State where the property is located", example = "Maharashtra")
	    private String state;

	    @Schema(description = "Country where the property is located", example = "India")
	    private String country;

	    @Schema(description = "6-digit postal code", example = "411044")
	    private Integer pincode;

}
