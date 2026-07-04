package com.healthcare.dtos;

import com.healthcare.entities.UserRole;

import lombok.Getter;
import lombok.Setter;

//(user id ,name, email , role , message)
@Getter
@Setter
public class AuthResponse {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private UserRole role;
	private String message;
}
