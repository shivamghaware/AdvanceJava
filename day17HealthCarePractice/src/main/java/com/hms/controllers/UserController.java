package com.hms.controllers;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dtos.ErrorResponseDTO;
import com.hms.dtos.AuthRequestDTO;
import com.hms.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userservice;

	@PostMapping
	@Operation(description = "User sign in")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthRequestDTO authrequest){
		try {
			return ResponseEntity.ok(userservice.authenticateUserByEmailPass(authrequest));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDTO(e.getMessage(),"Failed"));
		}
	}

}
