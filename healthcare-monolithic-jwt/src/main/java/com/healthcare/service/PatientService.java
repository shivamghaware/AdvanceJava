package com.healthcare.service;

import java.util.List;

import com.healthcare.dtos.PatientDTO;
import com.healthcare.entities.BloodGroup;

public interface PatientService {

	PatientDTO getPatientDetails(Long patientId);

	List<PatientDTO> getPatientsByBloodGroup(BloodGroup bloodGroup);

}
