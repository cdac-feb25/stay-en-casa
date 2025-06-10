package com.stayen.casa.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stayen.casa.authenticationservice.config.MongoConfig;
import com.stayen.casa.authenticationservice.dto.LoginRequestDTO;
import com.stayen.casa.authenticationservice.dto.LogoutDTO;
import com.stayen.casa.authenticationservice.dto.SignupRequestDTO;
import com.stayen.casa.authenticationservice.exception.credential.InvalidCredentialException;
import com.stayen.casa.authenticationservice.exception.credential.NoAccountFoundException;
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
@RequestMapping("/auth")
public class AuthController {

    private final MongoConfig mongoConfig;
	
	private final UserCredentialService userCredentialService;
	
	@Autowired
	public AuthController(UserCredentialService userCredentialService, MongoConfig mongoConfig) {
		this.userCredentialService = userCredentialService;
		this.mongoConfig = mongoConfig;
	}
	
	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
//		return ResponseEntity.ok("Hello World !!!");
//		return ResponseEntity.ok(loginRequestDTO);
		return ResponseEntity.ok(userCredentialService.loginUser(loginRequestDTO));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody SignupRequestDTO signupRequestDTO) {
//		return ResponseEntity.ok("Hello World !!!");
//		return ResponseEntity.ok(signupRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCredentialService.registerNewUser(signupRequestDTO));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody LogoutDTO logoutDTO) {
//		return ResponseEntity.ok(logoutDTO);
		return ResponseEntity.status(HttpStatus.OK).body(userCredentialService.logoutUser(logoutDTO));
	}
	
}
