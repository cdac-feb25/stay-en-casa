package com.stayen.casa.propertyservice.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIResponse {

	private LocalDateTime timeStamp;
	private String message;
	
	public APIResponse(String message)
	{
		this.timeStamp = LocalDateTime.now();
		this.message = message;
	}
	
}
