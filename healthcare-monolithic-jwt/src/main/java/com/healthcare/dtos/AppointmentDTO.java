package com.healthcare.dtos;
/*
 *  "appointmentId": 1,
    "appointmentStartDateTime": "2025-11-08T10:30:00",
    "firstName": "Rajiv",
    "lastName": "Mehta"

 */

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class AppointmentDTO {
	private Long appointmentId;
	private LocalDateTime appointmentStart;
	private String firstName;
	private String lastName;
}
