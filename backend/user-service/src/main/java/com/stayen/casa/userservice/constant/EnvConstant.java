package com.stayen.casa.userservice.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EnvConstant {

    @Value("${api-gateway-service-base-url}")
    private String apiGatewayServiceBaseUrl;

}
