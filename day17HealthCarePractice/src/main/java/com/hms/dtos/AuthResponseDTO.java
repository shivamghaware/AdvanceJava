package com.hms.dtos;

import com.hms.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthResponseDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private UserRole role;
	private String message;
}
