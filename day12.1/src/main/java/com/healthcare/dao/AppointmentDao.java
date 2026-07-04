package com.healthcare.dao;

import java.time.LocalDateTime;

public interface AppointmentDao {
//add a method to book new appointment
	String bookAppointment(Long patientId,Long doctorId,LocalDateTime appointmentStart);
}
