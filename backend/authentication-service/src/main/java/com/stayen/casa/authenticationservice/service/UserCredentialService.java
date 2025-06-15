package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.AuthResponseDTO;
import com.stayen.casa.authenticationservice.dto.LoginRequestDTO;
import com.stayen.casa.authenticationservice.dto.SignupRequestDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.dto.AuthErrorDTO;
import com.stayen.casa.authenticationservice.exception.credential.InvalidCredentialException;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;

public interface UserCredentialService {
	
	AuthResponseDTO registerNewUser(SignupRequestDTO signupRequestDTO);
	
	AuthResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
	
	SimpleResponseDTO logoutUser();
	
//	AuthResponseDTO validateUserCredential(LoginRequestDTO loginRequestDTO);

}
