package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.LoginRequestDTO;
import com.stayen.casa.authenticationservice.dto.LogoutDTO;
import com.stayen.casa.authenticationservice.dto.SignupRequestDTO;
import com.stayen.casa.authenticationservice.dto.TokenResponseDTO;
import com.stayen.casa.authenticationservice.exception.credential.InvalidCredentialException;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;

public interface UserCredentialService {
	
	TokenResponseDTO registerNewUser(SignupRequestDTO signupRequestDTO);
	
	TokenResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
	
	LogoutDTO logoutUser(LogoutDTO logoutDTO);
	
//	AuthResponseDTO validateUserCredential(LoginRequestDTO loginRequestDTO);

}
