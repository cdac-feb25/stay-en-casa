package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.stayen.casa.authenticationservice.model.JwtModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_tokens")
@NoArgsConstructor
@Getter
@Setter
public class UserToken extends BaseTimestampEntity {

	@Id
	@Field(name = "uid")
	private String uid;
	
	@Indexed(unique = true)
	private String email;
	
	private List<DeviceToken> tokens = new ArrayList<>();

	public UserToken(String uid, String email) {
		super.currentTimestamp();
		this.uid = uid;
		this.email = email;
	}
	
	public UserToken(String uid, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super.updateTimestamp(createdAt, updatedAt);
		this.uid = uid;
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
