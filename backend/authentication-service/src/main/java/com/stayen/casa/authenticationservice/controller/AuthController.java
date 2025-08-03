package com.stayen.casa.authenticationservice.controller;

import com.stayen.casa.authenticationservice.constant.Endpoints;
import com.stayen.casa.authenticationservice.dto.EmailDTO;
import com.stayen.casa.authenticationservice.dto.LogoutRequestDTO;
import com.stayen.casa.authenticationservice.dto.OtpPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stayen.casa.authenticationservice.dto.LoginSignupRequestDTO;
import com.stayen.casa.authenticationservice.service.UserCredentialService;

/**
 * <pre>
 * Auth Controller for handling 
 * 	1. login 
 * 	2. logout 
 * 	3. register
 * </pre>
 */
@RestController
@RequestMapping(Endpoints.Auth.BASE_URL) // http://localhost:9091/api/v1/auth/XXX
public class AuthController {

	private final UserCredentialService userCredentialService;
	
	@Autowired
	public AuthController(UserCredentialService userCredentialService) {
		this.userCredentialService = userCredentialService;
	}
	
	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}
	
	@PostMapping(Endpoints.Auth.LOGIN)
	public ResponseEntity<?> login(@RequestBody LoginSignupRequestDTO loginSignupRequestDTO) {
		System.out.println(loginSignupRequestDTO);
		return ResponseEntity
				.ok(userCredentialService.loginUser(loginSignupRequestDTO));
	}
	
	@PostMapping(Endpoints.Auth.SIGNUP)
	public ResponseEntity<?> register(@RequestBody LoginSignupRequestDTO loginSignupRequestDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userCredentialService.signupUser(loginSignupRequestDTO));
	}
	
	@PostMapping(Endpoints.Auth.LOGOUT)
	public ResponseEntity<?> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
		return ResponseEntity
				.ok(userCredentialService.logoutUser(logoutRequestDTO));
	}

	@PostMapping(Endpoints.Auth.FORGOT_PASSWORD)
	public ResponseEntity<?> forgotPassword(@RequestBody EmailDTO emailDTO) {
		return ResponseEntity
				.ok(userCredentialService.forgotPassword(emailDTO));
	}

	@PutMapping(Endpoints.Auth.VERIFY_AND_CHANGE_PASSWORD)
	public ResponseEntity<?> verifyOTPAndChangePassword(@RequestBody OtpPasswordDTO otpPasswordDTO) {
		return ResponseEntity
				.ok(userCredentialService.verifyOTPAndChangePassword(otpPasswordDTO));
	}


	
}
