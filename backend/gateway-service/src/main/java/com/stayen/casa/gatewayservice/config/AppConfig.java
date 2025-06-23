package com.stayen.casa.gatewayservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stayen.casa.gatewayservice.security.filter.JwtFilter;
import com.stayen.casa.gatewayservice.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

    @Bean
    public JwtFilter jwtFilter(JwtUtils jwtUtils, ObjectMapper mapper) {
        return new JwtFilter(jwtUtils, mapper);
    }

}
