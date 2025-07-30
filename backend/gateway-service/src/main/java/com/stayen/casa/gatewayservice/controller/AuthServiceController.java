package com.stayen.casa.gatewayservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.gatewayservice.constant.BodyConstant;
import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.UserContext;
import com.stayen.casa.gatewayservice.dto.AuthTokenResponseDTO;
import com.stayen.casa.gatewayservice.helper.RestTemplateHelper;
import com.stayen.casa.gatewayservice.model.User;
import com.stayen.casa.gatewayservice.validator.ResponseValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1/auth")
@RequestMapping(Endpoints.Auth.BASE_URL)
public class AuthServiceController {
    private static final String CLASS_NAME = AuthServiceController.class.getSimpleName();

    private final String appDomain;
    private final String authServiceDomain;
    private final RestTemplateHelper restTemplateHelper;

    public AuthServiceController(EnvConstant envConstant, RestTemplateHelper restTemplateHelper) {
        this.appDomain = envConstant.getAppDomain();
        this.authServiceDomain = envConstant.getAuthServiceDomain();
        this.restTemplateHelper = restTemplateHelper;
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Some message");
    }

    @PostMapping(Endpoints.Auth.LOGIN)
    public ResponseEntity<?> login(@RequestBody Map<String, Object> receivedPayload) {
        String url = authServiceDomain + Endpoints.Auth.BASE_URL + Endpoints.Auth.LOGIN;

//        return restTemplateHelper.POST(url, receivedPayload, String.class);
        ResponseEntity<AuthTokenResponseDTO> response = restTemplateHelper.POST(url, receivedPayload, AuthTokenResponseDTO.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(ResponseValidator.getHeaderWithJwtRefreshTokenCookies(appDomain, response))
                .body(response.getBody());
    }

    @PostMapping(Endpoints.Auth.SIGNUP)
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> receivedPayload) {
        String url = authServiceDomain + Endpoints.Auth.BASE_URL + Endpoints.Auth.SIGNUP;
        System.out.println(receivedPayload);

//        return restTemplateHelper.POST("http://localhost:9090/api/v1/auth/test", receivedPayload, String.class);
        return restTemplateHelper.POST(url, receivedPayload, String.class);
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
