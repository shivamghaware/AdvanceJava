package com.healthcare.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
	private String firstName;
	private String lastName;
	private LocalDate dob;
}
