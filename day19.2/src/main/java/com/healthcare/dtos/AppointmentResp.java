package com.healthcare.dtos;
/*
 * "appointmentId": 1,
    "appointmentTS": "2025-11-08T10:30:00",
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
public class AppointmentResp {
	private Long appointmentId;
	private LocalDateTime startDateTime;
	private String firstName;
	private String lastName;
}
