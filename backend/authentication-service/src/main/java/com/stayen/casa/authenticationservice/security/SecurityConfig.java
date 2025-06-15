package com.stayen.casa.authenticationservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.stayen.casa.authenticationservice.security.filters.JwtAuthFilter;
import com.stayen.casa.authenticationservice.security.utils.JwtUtils;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter) throws Exception {
		httpSecurity
				.csrf((csrf) -> csrf.disable())
				.httpBasic((basicAuth) -> basicAuth.disable())
				.formLogin((formLogin) -> formLogin.disable())
				.sessionManagement((sessionManager) -> {
					sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.authorizeHttpRequests((httpRequest) -> {
					httpRequest
							.requestMatchers(
									"/auth/test",
									"/auth/register/**", "/auth/login/**", 
									"/swagger-ui/**", "/swagger-ui.html", 
									"/v3/api-docs/**", "/webjars/**"
									).permitAll()
							.anyRequest().authenticated();
				});

		httpSecurity
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
}
