package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stayen.casa.authenticationservice.model.JwtModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"uid", "accessToken", "refreshToken", "createdAt", "updatedAt"})
public class AuthResponseDTO extends BaseTimestampDTO {

	private String uid;
	
	private String accessToken;
	
	private String refreshToken;
	
	private AuthResponseDTO from() {
		super.currentTimestamp();
		return this;
	}
	
	private AuthResponseDTO from(LocalDateTime createdAt, LocalDateTime updatedAt) {
		super.updateTimestamp(createdAt, updatedAt);
		return this;
	}

	public static AuthResponseDTO from(JwtModel jwtModel, String accessToken, String refreshToken) {
		return new AuthResponseDTO(jwtModel.getUid(), accessToken, refreshToken)
				.from();
	}
	
	public static AuthResponseDTO from(JwtModel jwtModel, String accessToken, String refreshToken, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new AuthResponseDTO(jwtModel.getUid(), accessToken, refreshToken)
				.from(createdAt, updatedAt);
	}
}
