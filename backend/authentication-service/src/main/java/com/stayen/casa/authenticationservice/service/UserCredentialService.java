package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.*;

public interface UserCredentialService {
	
	AuthTokenResponseDTO signupUser(LoginSignupRequestDTO loginSignupRequestDTO);
	
	AuthTokenResponseDTO loginUser(LoginSignupRequestDTO loginSignupRequestDTO);
	
	SimpleResponseDTO logoutUser(LogoutRequestDTO logoutRequestDTO);

	SimpleResponseDTO forgotPassword(EmailDTO emailDTO);

	SimpleResponseDTO verifyOTPAndChangePassword(OtpPasswordDTO otpPasswordDTO);

}
