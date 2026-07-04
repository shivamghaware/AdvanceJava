package com.healthcare.service;

import java.util.List;

import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AppointmentResp;
import com.healthcare.dtos.BookAppointment;

public interface AppointmentService {

	List<AppointmentResp> getUpcomingPatientAppointments(Long patientId);

	ApiResponse bookAppointment(BookAppointment request);

}
