package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.BloodGroup;
import com.healthcare.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByBloodGroup(BloodGroup bloodGroup);
}
