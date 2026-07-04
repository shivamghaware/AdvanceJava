package com.healthcare.dao;

import java.time.LocalDateTime;

public interface AppointmentDao {
//add a method to book appointment
	String bookPatientAppointment(Long doctorId, Long patientId, LocalDateTime appointmentstart);
}
