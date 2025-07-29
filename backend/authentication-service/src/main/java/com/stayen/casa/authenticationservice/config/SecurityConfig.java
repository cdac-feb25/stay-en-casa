package com.stayen.casa.authenticationservice.config;

import com.stayen.casa.authenticationservice.filter.ApiGatewayAccessFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final ApiGatewayAccessFilter apiGatewayAccessFilter;

	@Autowired
	public SecurityConfig(ApiGatewayAccessFilter apiGatewayAccessFilter) {
		this.apiGatewayAccessFilter = apiGatewayAccessFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf((csrf) -> csrf.disable())
				.httpBasic((basicAuth) -> basicAuth.disable())
				.formLogin((formLogin) -> formLogin.disable())
				.sessionManagement((sessionManager) -> {
					sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.cors((cors) -> {
					cors.configurationSource(new CorsConfigurationSource() {
						@Override
						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
							CorsConfiguration config = new CorsConfiguration();
							config.setAllowedOrigins(List.of("http://localhost:9090"));
							return config;
						}
					});
				})
				.authorizeHttpRequests((httpRequest) -> {
					httpRequest
							.anyRequest().permitAll();
				})
				.addFilterBefore(apiGatewayAccessFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}
