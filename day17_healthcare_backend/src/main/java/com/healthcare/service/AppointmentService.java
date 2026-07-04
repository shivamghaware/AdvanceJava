package com.healthcare.service;

import java.util.List;

import com.healthcare.dtos.AppointmentResp;

public interface AppointmentService {

	List<AppointmentResp> getUpcomingPatientAppointments(Long patientId);

}
