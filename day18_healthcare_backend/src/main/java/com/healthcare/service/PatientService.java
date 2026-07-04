package com.healthcare.service;

import com.healthcare.dtos.PatientRespDTO;

public interface PatientService {

	PatientRespDTO getPatientDetails(Long patientId);

}
