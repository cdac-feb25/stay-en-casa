package com.stayen.casa.gatewayservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.gatewayservice.constant.BodyConstant;
import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.UserContext;
import com.stayen.casa.gatewayservice.helper.RestTemplateHelper;
import com.stayen.casa.gatewayservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1/auth")
@RequestMapping(Endpoints.Auth.BASE_URL)
public class AuthServiceController {
    private static final String CLASS_NAME = AuthServiceController.class.getSimpleName();

    private final RestTemplateHelper restTemplateHelper;
    private final ObjectMapper mapper;
    private final String authServiceDomain;

    public AuthServiceController(RestTemplateHelper restTemplateHelper, ObjectMapper mapper, EnvConstant envConstant) {
        this.restTemplateHelper = restTemplateHelper;
        this.mapper = mapper;
        this.authServiceDomain = envConstant.getAuthServiceDomain();
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Some message");
    }

    @PostMapping(Endpoints.Auth.LOGIN)
    public ResponseEntity<?> login(@RequestBody Map<String, Object> originalBody) {
        String url = authServiceDomain + Endpoints.Auth.BASE_URL + Endpoints.Auth.LOGIN;

        return restTemplateHelper.POST(url, originalBody, String.class);
    }

    @PostMapping(Endpoints.Auth.SIGNUP)
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> originalBody) {
        String url = authServiceDomain + Endpoints.Auth.BASE_URL + Endpoints.Auth.SIGNUP;
        System.out.println(originalBody);

//        return restTemplateHelper.POST("http://localhost:9090/api/v1/auth/test", originalBody, String.class);
        return restTemplateHelper.POST(url, originalBody, String.class);
    }

    @PostMapping(Endpoints.Auth.LOGOUT)
    public ResponseEntity<?> logout() {
        String url = authServiceDomain + Endpoints.Auth.BASE_URL + Endpoints.Auth.LOGOUT;

        User loggedInUser = UserContext.getLoggedInUser();
        System.out.println("Logging out of :: " + loggedInUser);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put(BodyConstant.UID, loggedInUser.getUid());
        requestBody.put(BodyConstant.DEVICE_ID, loggedInUser.getDeviceId());

        return restTemplateHelper.POST(url, requestBody, String.class);
    }

}
