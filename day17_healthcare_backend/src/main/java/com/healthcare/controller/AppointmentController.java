package com.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dtos.ApiResponse;
import com.healthcare.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
	private final AppointmentService appointmentService;
/*
 * . List Upcoming patient appointments
i/p :  user id (path variable )
URL - http://host:port/appointments/patients/{patientId}/upcoming
Method - GET
Path var- patient id
Response - list of upcoming appointment dtos
Eg .
[{
    "appointmentId": 1,
    "appointmentTS": "2025-11-08T10:30:00",
    "firstName": "Rajiv",
    "lastName": "Mehta"
 }]
 */  
	@GetMapping("/patients/{patientId}/upcoming")
	public ResponseEntity<?> getUpcomingPatientAppointments(@PathVariable Long patientId) {
		System.out.println("in patient upcoming "+patientId);
		try {
			//call service layer method -> add it as a body content in RespEntity
			return ResponseEntity.ok(appointmentService.getUpcomingPatientAppointments(patientId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
}
