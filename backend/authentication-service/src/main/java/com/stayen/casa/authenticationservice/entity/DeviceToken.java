package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceToken {
	
	@Indexed(unique = true)
	private String deviceId;

	private String refreshToken;
	
	private String lastRefreshToken;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	public DeviceToken(String deviceId, String refreshToken, String lastRefreshToken) {
		this.deviceId = deviceId;
		this.refreshToken = refreshToken;
		this.lastRefreshToken = lastRefreshToken;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
//	@Override
//	public boolean equals(Object anObject) {
//        if (this == anObject) {
//            return true;
//        }
//        
//        if(!(anObject instanceof DeviceToken)) {
//        	return false;
//        }
//        
//        return (this.deviceId.equals(((DeviceToken) anObject).deviceId));
//    }
//
//	@Override
//	public int hashCode() {
//		return this.deviceId.toLowerCase().hashCode();
//	}
	
}
