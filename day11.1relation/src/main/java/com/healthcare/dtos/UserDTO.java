package com.healthcare.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
	private String firstName;
	private String lastName;
	private LocalDate dob;
}
