package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import com.stayen.casa.authenticationservice.model.JwtModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO extends BaseDTO {

	private String accessToken;

	private String refreshToken;

	private TokenResponseDTO(JwtModel jwtModel, String accessToken, String refreshToken, LocalDateTime creationAt, LocalDateTime updatedAt) {
		super(jwtModel.getEmail(), jwtModel.getDeviceId(), creationAt, updatedAt);
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public static TokenResponseDTO from(JwtModel jwtModel, String accessToken, String refreshToken) {
		return new TokenResponseDTO(jwtModel, accessToken, refreshToken, LocalDateTime.now(), LocalDateTime.now());
	}
	
	// TODO : when calling from refresh token, we will receive token creation/updation timestamp
	public static TokenResponseDTO from(JwtModel jwtModel, String accessToken, String refreshToken, LocalDateTime creationAt, LocalDateTime updatedAt) {
		return new TokenResponseDTO(jwtModel, accessToken, refreshToken, creationAt, updatedAt);
	}

}
