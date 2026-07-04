package com.hms.dtos;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
		Long id,                       // Matches a.id
	    LocalDateTime startDateTime,   // Matches a.startDateTime
	    String myDoctorUserDetailsFirstName, // Automatically traverses a.myDoctor.userDetails.firstName
	    String myDoctorUserDetailsLastName  // Automatically traverses a.myDoctor.userDetails.lastName
		) {}
