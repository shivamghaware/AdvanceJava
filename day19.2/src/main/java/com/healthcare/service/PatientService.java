package com.healthcare.service;

import java.util.List;

import com.healthcare.dtos.PatientRespDTO;

public interface PatientService {

	PatientRespDTO getPatientDetails(Long patientId);

	List<PatientRespDTO> getAllPatients();

}
