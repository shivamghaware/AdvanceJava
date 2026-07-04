package com.hms.service;

import java.util.List;

import com.hms.dtos.AppointmentResponseDTO;

public interface AppointmentService {

	List<AppointmentResponseDTO> getUpcomingPatientAppointments(Long patientId);
	
}
