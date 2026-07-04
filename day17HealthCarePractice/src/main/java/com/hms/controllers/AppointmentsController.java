package com.hms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dtos.ErrorResponseDTO;
import com.hms.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentsController {
	private final AppointmentService appointmentService;
	
	@GetMapping("/patients/{patientId}/upcoming")
	public ResponseEntity<?> getUpcomingPatientAppointments(@PathVariable Long patientId){
		try {
			return ResponseEntity.ok(appointmentService.getUpcomingPatientAppointments(patientId));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage(), "Failed"));
		}
	}
	
}
