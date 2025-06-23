package com.stayen.casa.gatewayservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRouteController {

    @PostMapping("/login")
    public ResponseEntity<?> login() {


        return ResponseEntity.ok().body("login");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup() {


        return ResponseEntity.ok().body("signup");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {


        return ResponseEntity.ok().body("logout");
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshToken() {


        return ResponseEntity.ok().body("refresh token");
    }


}
