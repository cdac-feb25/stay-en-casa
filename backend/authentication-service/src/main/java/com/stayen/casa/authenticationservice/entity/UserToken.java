package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
	
	/**
	 * Rotation of Refresh Token Process
	 * 
	 * 
	 * 
	 * 
	 * @param token
	 * @return
	 */
	public boolean isLastRefreshToken(String token) {
		return this.tokens
				.stream()
				.anyMatch((deviceToken) -> {
					return deviceToken.getLastRefreshToken().equals(token);
				});
	}
	
	/**
	 * Check and rotate current refresh token
	 * 
	 * @param token
	 * @return true if token matched and rotated with lastRefreshToken, else false
	 */
	public boolean ifRefreshAndRotated(String token) {
		return this.tokens
				.stream()
				.anyMatch((deviceToken) -> {
					if(deviceToken.getRefreshToken().equals(token)) {
						deviceToken.setLastRefreshToken(deviceToken.getRefreshToken());
						return true;
					}
					return false;
				});
	}
	
	public void setNewRefreshToken(String deviceId, String newRefreshToken) {
		this.tokens
			.forEach((deviceToken) -> {
				if(deviceToken.getDeviceId().equals(deviceId)) {
					deviceToken.setRefreshToken(newRefreshToken);
				}
			});
	}
	
	
	
	
//	public String getRefreshTokenForDeviceId(String deviceId) {
//		AtomicReference<String> refreshToken = new AtomicReference<>();
//		
//		this.tokens
//			.stream()
//			.filter((deviceToken) -> {
//				return deviceToken.getDeviceId().equals(deviceId);
//			})
//			.forEach((deviceToken) -> {
//				refreshToken.set(deviceToken.getRefreshToken());
//			});
//		
//		return refreshToken.get();
//	}
	
}
