package com.stayen.casa.gatewayservice.security;

import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.disable())
//                .cors()
                .httpBasic((basicSecurity) -> basicSecurity.disable())
                .formLogin((formLogin) -> formLogin.disable())
                .sessionManagement((sessionManager) -> {
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers(
                                    "api/v1/auth/test", "api/v1/users/test",
                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.LOGIN),  // "/api/v1/auth/login",
                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.SIGNUP),  // "/api/v1/auth/signup",
                                    "/swagger-ui/**", "/swagger-ui.html",
                                    "/v**/api-docs/**"
                            ).permitAll()
                            .anyRequest().authenticated();
//                            .anyRequest().permitAll();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
