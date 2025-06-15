package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public abstract class BaseDTO {

	private String email;
	
	private String deviceId;
	
	@CreatedDate
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedAt;
	
	public BaseDTO(String email, String deviceId) {
		this.email = email;
		this.deviceId = deviceId;
	}
	
	public BaseDTO(String email, String deviceId, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.email = email;
		this.deviceId = deviceId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public void updateTimestamp(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
