package com.healthcare.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.PatientRespDTO;
import com.healthcare.entities.Patient;
import com.healthcare.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
	//depcy
	private final PatientRepository patientRepository;
	private final ModelMapper mapper;

	@Override
	public PatientRespDTO getPatientDetails(Long patientId) {
		// 1. get patient details by userid(same as patient id)
		Patient patient=patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Patient ID!!!"));
		//patient : persistent entity
		return mapper.map(patient, PatientRespDTO.class);
	}

	@Override
	public List<PatientRespDTO> getAllPatients() {
		// TODO Auto-generated method stub
		return patientRepository.findAll()
				.stream()
				.map(patient -> mapper.map(patient, PatientRespDTO.class))
				.toList();
	}
	

}
