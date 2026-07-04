package com.healthcare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	/*
	 * Configure spring bean to customize sec filter chain
	 * 
	 * 
	 */
	@Bean
	SecurityFilterChain customizeSecFilterChain(HttpSecurity http) throws Exception {
		// 1. Disable CSRF protection
		http.csrf(csrf -> csrf.disable());
		// 2. Disable HttpSession - Tell Spring security - DO NOT create Http Session
		// object to store security context
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// 3. Enable Basic Auth support - in default manner
		http.httpBasic(Customizer.withDefaults());
		// 4. Customize URL based authorization rules
		// public endpoints
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/users/signin", "/patients/signup").permitAll()
				// Only Patient can book
				.requestMatchers(HttpMethod.POST, "/appointments").hasRole("PATIENT")
				// Only admin can view all patients
				.requestMatchers("/patients").hasRole("ADMIN")
				// only doc can mark appointment as complete
				.requestMatchers("/appointments/doctor/mark_complete").hasRole("DOCTOR").anyRequest().authenticated());
		return http.build();
	}

	// configure BCryptPasswordEncoder as spring bean
	// adds random salt + SHA - 256 (one way hashing)
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
