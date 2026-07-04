package com.hms.mappers;

import org.mapstruct.Mapper;

import com.hms.dtos.PatientResponseDTO;
import com.hms.entities.Patient;

@Mapper
public interface PatientMapper {
    PatientResponseDTO toPatientResponseDTO(Patient patient);
}