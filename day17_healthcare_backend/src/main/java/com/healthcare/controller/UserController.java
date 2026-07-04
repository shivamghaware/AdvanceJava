package com.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AuthRequest;
import com.healthcare.dtos.AuthResponse;
import com.healthcare.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;


	/*
	 * 1. Patient Login / Doctor Login / Admin (User Login) common
	 * 
	 * URL - http://host:port/users/signin Method - POST (for security , JWT
	 * generation later, JSON payload) Eg . Patient Logs in Payload - email ,
	 * password (AuthRequest - DTO : java record) Success Resp -Sc 200 Auth Resp
	 * (user id ,name, email , role , message) Failure Resp - SC 401 ApiResp
	 * DTO(status : succes | failure , timestamp , message)
	 * 
	 */
	@PostMapping("/signin")
	@Operation(description = "User sign in")
	public ResponseEntity<?> userSignIn(@RequestBody AuthRequest request) {
		System.out.println("in user sign in " + request);
		try {
			// invoke service layer method
			AuthResponse authResp = userService.authenticateUser(request);
			return ResponseEntity.ok(authResp);
		} catch (RuntimeException e) {
			System.out.println("err " + e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED) // SC 401
					.body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
}
