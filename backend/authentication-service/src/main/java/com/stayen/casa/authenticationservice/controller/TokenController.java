package com.stayen.casa.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stayen.casa.authenticationservice.service.UserTokenService;

/**
 * <pre>
 * Auth Controller for handling 
 * 	1. login 
 * 	2. logout 
 * 	3. register
 * </pre>
 */
@RestController
@RequestMapping("/auth/token")
public class TokenController {
	
	private final UserTokenService userTokenService;
	
	@Autowired
	public TokenController(UserTokenService userTokenService) {
		this.userTokenService = userTokenService;
	}

	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken() {
		return ResponseEntity.ok(userTokenService.refreshToken());
	}
	
}
