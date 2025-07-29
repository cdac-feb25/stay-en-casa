package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.AuthTokenResponseDTO;
import com.stayen.casa.authenticationservice.dto.LoginSignupRequestDTO;
import com.stayen.casa.authenticationservice.dto.LogoutRequestDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;

public interface UserCredentialService {
	
	AuthTokenResponseDTO signupUser(LoginSignupRequestDTO loginSignupRequestDTO);
	
	AuthTokenResponseDTO loginUser(LoginSignupRequestDTO loginSignupRequestDTO);
	
	SimpleResponseDTO logoutUser(LogoutRequestDTO logoutRequestDTO);
	
}
