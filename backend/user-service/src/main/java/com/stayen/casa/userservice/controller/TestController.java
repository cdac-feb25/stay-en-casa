package com.stayen.casa.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Value("${jwt-secret-key}")
	String jwtSecretKey;
	
	@GetMapping("/key")
	public String test() {
		return jwtSecretKey;
	}
}
