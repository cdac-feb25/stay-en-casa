package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.AuthResponseDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.dto.AuthErrorDTO;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.model.JwtModel;

public interface UserTokenService {
	
	UserToken fetchUserToken(String email);
	
	SimpleResponseDTO invalidateDeviceToken();

	AuthResponseDTO generateToken(JwtModel jwtModel);
	
}
