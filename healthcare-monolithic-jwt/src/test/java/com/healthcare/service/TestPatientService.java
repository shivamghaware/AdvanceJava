package com.healthcare.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.PatientDTO;

@SpringBootTest //scans all the the components
class TestPatientService {
	@Autowired
	private PatientService patientService;

	@Test
	void testGetPatientDetailsSuccess() {
		PatientDTO patientDetails = patientService.getPatientDetails(5l);
		assertTrue(patientDetails.getFamilyHistory().equals("Diabetes"));
	}
	@Test
	void testGetPatientDetailsFail() {
//		PatientDTO patientDetails = patientService.getPatientDetails(15l);
//		assertTrue(patientDetails.getFamilyHistory().equals("Diabetes"));
		ResourceNotFoundException exc = assertThrows(ResourceNotFoundException.class,() -> patientService.getPatientDetails(15l));
		assertTrue(exc.getMessage().contains("Invalid Patient id"));
	}

}
