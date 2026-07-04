package com.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.entities.Status;
import com.hms.dtos.AppointmentResponseDTO;
import com.hms.repo.AppointmentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
	private final AppointmentRepository appointmentRepo;
	
	@Override
	public List<AppointmentResponseDTO> getUpcomingPatientAppointments(Long patientId) {
		return appointmentRepo.findByMyPatientIdAndStatus(patientId,Status.SCHEDULED);
	}

}
