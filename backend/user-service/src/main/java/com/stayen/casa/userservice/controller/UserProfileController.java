package com.stayen.casa.userservice.controller;

import com.stayen.casa.userservice.constant.Endpoints;
import com.stayen.casa.userservice.constant.UserContext;
import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.model.User;
import com.stayen.casa.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.USER_BASE_URL + Endpoints.USER_PROFILE)
public class UserProfileController {

	private final UserProfileService userProfileService;

	@Autowired
	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@GetMapping("/test")
	public String test() {
		return "Request completed !!!";
	}

	@GetMapping
	public ResponseEntity<?> fetchUserDetail() {
		User loggedInUser = UserContext.getLoggedInUser();

		return ResponseEntity
				.ok(userProfileService.fetchUserProfile(loggedInUser.getUid()));
	}

	@PostMapping
	public ResponseEntity<?> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userProfileService.createUserProfile(userProfileDTO));
	}

	@PutMapping
	public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
		User loggedInUser = UserContext.getLoggedInUser();

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userProfileService.updateUserProfile(loggedInUser, userProfileDTO));
	}
	
}
