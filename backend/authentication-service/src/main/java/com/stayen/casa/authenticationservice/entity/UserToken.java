package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_tokens")
@NoArgsConstructor
@Getter
@Setter
public class UserToken {

	@Id
//	@Indexed(unique = true)
	@Field(name = "email")
	private String email;
	
	private List<DeviceToken> tokens = new ArrayList<>();
	
	/**
	 * Constructor
	 * 
	 * @param email
	 */
	public UserToken(String email) {
		this.email = email;
	}
	
	/**
	 * Helper Functions
	 */
	
	/**
	 * Check if token present for the given deviceId.
	 * 
	 * @param deviceId
	 * @return true if any token were removed, else false
	 */
	public boolean removeDeviceToken(String deviceId) {
		return this.tokens.removeIf((deviceToken) -> {
			return deviceToken.getDeviceId().equals(deviceId);
		});
	}
	
	public void addDeviceToken(DeviceToken deviceToken) {
		this.tokens.add(deviceToken);
	}
	
}
