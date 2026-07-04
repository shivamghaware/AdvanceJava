package com.hms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dtos.ErrorResponseDTO;
import com.hms.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientsController {
	private final PatientService patientService;
	
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable Long patientId){
		try {
			return ResponseEntity.ok(patientService.getPatientDetailsById(patientId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponseDTO(e.getMessage(), "Failed"));
		}
	}
	
	
}
