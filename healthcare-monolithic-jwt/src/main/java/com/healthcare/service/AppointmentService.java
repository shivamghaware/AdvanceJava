package com.healthcare.service;

import java.util.List;

import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AppointmentDTO;
import com.healthcare.dtos.BookAppointment;
import com.healthcare.dtos.CompleteAppointmentDetails;

public interface AppointmentService {

	List<AppointmentDTO> getPatientAppointments(Long patientId);
	ApiResponse bookPatientAppointment(BookAppointment request);
	ApiResponse cancelAppointmentByPatient(Long appointmentId, Long patientId);
	ApiResponse completeAppointmentByDoctor(Long appointmentId, Long doctorId);
	List<CompleteAppointmentDetails> getAllAppointments();
}
