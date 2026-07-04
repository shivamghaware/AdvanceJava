package com.healthcare.dao;

import com.healthcare.entities.Doctor;

public interface DoctorDao {
//add a method to sign up a doctor
	String registerDoctor(Doctor doctor);

	Doctor getDoctorDetails(Long doctorId);
}
