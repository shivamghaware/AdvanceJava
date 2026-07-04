package com.healthcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AuthRequest;
import com.healthcare.dtos.AuthResp;
import com.healthcare.repository.UserRepository;
import com.healthcare.security.CustomUserDetailsImpl;
import com.healthcare.security.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	// constr based D.I
	private final UserRepository userRepo;
	private final ModelMapper mapper;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authManager;
	private final JwtUtils jwtUtils;

	@Override
	public AuthResp authenticateUser(AuthRequest request) {
		/*
		 * 1. Invoke Spring security supplied AuthenticationManager's - authenticate
		 * method API of AuthenticationManager interface public Authentication
		 * authenticate(Authentication auth); Authentication i/f - implemented by
		 * UserNamePasswordAuthenticationToken(Object username|email , Object
		 * credentials) 1.1 - Create UserNamePasswordAuthenticationToken , to hold email
		 * & password
		 */
		UsernamePasswordAuthenticationToken holder = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		System.out.println("before " + holder.isAuthenticated());// false
		/*
		 * 1.2 call authenticate method - in case of failure Spring security throws
		 * AuthenticationException - un checked exception
		 */
		Authentication fullyAutheticatedDetails = authManager.authenticate(holder);
		// => authentication successful
		System.out.println("after  " + fullyAutheticatedDetails.isAuthenticated());// t
		System.out.println(fullyAutheticatedDetails.getPrincipal());// custom user details
		CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) fullyAutheticatedDetails.getPrincipal();

		/*
		 * 1.3 In case of successful authentication - create JWT & send it in auth
		 * response.
		 */

		return new AuthResp("Login Successful !", jwtUtils.generateJwt(userDetails));
	}

	@Override
	public ApiResponse encrytPasswords() {
		userRepo.findAll().forEach(user -> user.setPassword(encoder.encode(user.getPassword())));
		return new ApiResponse("Passwords encoded ...", "Success");
	}

}
