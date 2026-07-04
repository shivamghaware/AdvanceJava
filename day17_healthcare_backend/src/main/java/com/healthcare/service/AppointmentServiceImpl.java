package com.healthcare.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.dtos.AppointmentResp;
import com.healthcare.entities.Status;
import com.healthcare.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
	private final AppointmentRepository appointmentRepository;

	@Override
	public List<AppointmentResp> getUpcomingPatientAppointments(Long patientId) {
		//call dao's method
		return appointmentRepository.getPatientAppointments(patientId,Status.SCHEDULED);
	}

}
