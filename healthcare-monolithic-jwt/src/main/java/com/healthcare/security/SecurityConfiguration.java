package com.healthcare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration // declares a spring bean containing bean config - equivalent to xml config file
				// - to declare spring bean
@EnableWebSecurity // to specify the customization in Spring security
@EnableMethodSecurity // to enable method level authorization
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final CustomJWTVerificationFilter customJWTVerificationFilter;

	/*
	 * Configure spring bean to customize security filter chain
	 * 
	 */
	@Bean // <bean id ,class..../>
	SecurityFilterChain customizeSecurityFilterChain(HttpSecurity http) throws Exception {
		// 1. Disable CSRF protection - since it's REST API - stateless
		http.csrf(csrf -> csrf.disable());
		// 2. Disable HttpSession creation - Stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// 3. Configure Basic Authentication - with default
		// http.httpBasic(Customizer.withDefaults());
		// 4. Configure auth rules - URL based - public end points
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/users/signin", "/patients/signup",
						"/users/encryt_passwords")
				.permitAll()
				// Only Patient can book the appointment
				.requestMatchers(HttpMethod.POST, "/appointments").hasRole("PATIENT")
				// only patient can see its details
				.requestMatchers(HttpMethod.GET, "/patients/{patientId}").hasRole("PATIENT")
				// Only Doctor can change the appointment status - complete
				/// {appointmentId}/doctors/{doctorId}/complete
				.requestMatchers(HttpMethod.PATCH, "/appointments/{appointmentId}/doctors/{docId}/complete")
				.hasRole("DOCTOR")
				// Only Admin can see all the patients with specific blood group
				.requestMatchers(HttpMethod.GET, "/patients/bloodgroup/{bloodGroup}").hasRole("ADMIN")
				// Only authenticated uses can access remaining end points.
				.anyRequest().authenticated());
		// add custom jwt filter - before 1st authentication filter -
		http.addFilterBefore(customJWTVerificationFilter, UsernamePasswordAuthenticationFilter.class);
		// HttpSecurity - Builder to build sec filter chain.
		return http.build();
	}

	// Configure Spring Security supplied PasswordEncoder as spring bean
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// configure Spring Security supplied AuthenticationManager as spring bean
	// provider - AuthConfig
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
