package com.stayen.casa.authenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Auth Controller for handling 
 * 	1. login 
 * 	2. logout 
 * 	3. register
 * </pre>
 */
@RestController
@RequestMapping("/api/token")
public class TokenController {

	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}
	
}
