package com.healthcare.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.PatientDTO;
import com.healthcare.entities.BloodGroup;
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
	public PatientDTO getPatientDetails(Long patientId) {
		Patient entity=patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Patient id!!!!!"));
		//entity : persistent , entity -> dto
		return mapper.map(entity, PatientDTO.class);
	}

	@Override
	public List<PatientDTO> getPatientsByBloodGroup(BloodGroup bloodGroup) {
		List<Patient> patientList = patientRepository.findByBloodGroup(bloodGroup);
		return patientList.stream() //Stream<Patient>
				.map(patient -> mapper.map(patient, PatientDTO.class))//Stream<DTO>
				.toList();
	}
	
	

}
