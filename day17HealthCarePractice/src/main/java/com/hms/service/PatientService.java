package com.hms.service;

import com.hms.dtos.PatientResponseDTO;

public interface PatientService {

	PatientResponseDTO getPatientDetailsById(Long patientId);
}
