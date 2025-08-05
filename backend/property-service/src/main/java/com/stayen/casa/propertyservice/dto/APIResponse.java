package com.stayen.casa.propertyservice.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//DTO :  Reponse DTO : to send API response from REST Server ---> Rest Client

@Getter
@Setter
@NoArgsConstructor
public class APIResponse {

	private LocalDateTime timeStamp;
	private String message;
	private String propertyId;
	
	public APIResponse(String message)
	{
		this.timeStamp = LocalDateTime.now();
		this.message = message;
	}
	
	public APIResponse(String message, String propertyId) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.propertyId = propertyId;
    }
	
}
