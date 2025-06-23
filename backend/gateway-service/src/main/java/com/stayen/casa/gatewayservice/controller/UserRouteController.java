package com.stayen.casa.gatewayservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserRouteController {

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {


        return ResponseEntity.ok().body("profile");
    }


}
