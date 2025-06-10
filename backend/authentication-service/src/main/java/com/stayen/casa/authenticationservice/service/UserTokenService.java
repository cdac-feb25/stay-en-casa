package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.LogoutDTO;
import com.stayen.casa.authenticationservice.dto.TokenResponseDTO;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.model.JwtModel;

public interface UserTokenService {
	
	UserToken getValidUserToken(String email);
	
	LogoutDTO invalidateDeviceToken(LogoutDTO logoutDTO);

	TokenResponseDTO generateToken(JwtModel jwtModel);
	
}
