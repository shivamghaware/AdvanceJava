package com.healthcare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dtos.AuthRequest;
import com.healthcare.dtos.AuthResp;
import com.healthcare.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController // @Controller - cls level + @ResponseBody - added implicitly on
//ret types of all request handling method
@RequestMapping("/users")
@RequiredArgsConstructor // Lombok - generates the ctor with final & non null fields
public class UserController {
	// dependency - constr based D.I
	private final UserService userService;

	/*
	 * Desc - User sign in 
	 * URL - http://host:port/users/signin 
	 * Method - POST (for
	 * security , JWT generation, JSON payload) 
	 * Eg . Patient Logs in 
	 * Payload - email , password (AuthRequest - DTO ) 
	 * Success Resp -SC 200 Auth Resp (name, message + JWT) 
	 * Failure Resp - SC 401 ApiResp DTO(status :
	 * succes | failure , timestamp , message)
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> userSignIn(@RequestBody @Valid AuthRequest request) {
		System.out.println("in user signin " + request);
	
			// invoke service layer
			AuthResp resp = userService.authenticateUser(request);
			return ResponseEntity.ok(resp);

	}
	/*
	 * Desc - encrypt all password 
	 *  - Required only till sign up endpoint is added
	 *  Method - PATCH
	 *  Resp - ApiResp - passwords encoded !
	 */
	@PatchMapping("/encryt_passwords")
	public ResponseEntity<?> encrytPasswords() {
		System.out.println("in enc passwords ...");
		return ResponseEntity.ok(userService.encrytPasswords());
	}
}
