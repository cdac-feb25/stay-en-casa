package com.stayen.casa.gatewayservice.security;

import com.stayen.casa.gatewayservice.security.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
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
                                    "/api/v1/auth/login",
                                    "/api/v1/auth/signup",
                                    "/swagger-ui/**", "/swagger-ui.html",
                                    "/v3/api-docs/**", "/webjars/**"
                            ).permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
