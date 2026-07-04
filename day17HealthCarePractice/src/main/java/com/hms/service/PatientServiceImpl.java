package com.hms.service;

import org.springframework.stereotype.Service;

import com.hms.dtos.PatientResponseDTO;
import com.hms.entities.Patient;
import com.hms.exceptions.ResourceNotFoundException;
import com.hms.mappers.PatientMapper;
import com.hms.repo.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;

	@Override
	public PatientResponseDTO getPatientDetailsById(Long patientId) {
		Patient patient=patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient With ID: "+patientId+" Not Found!"));
		
		return patientMapper.toPatientResponseDTO(patient);
	}

}
