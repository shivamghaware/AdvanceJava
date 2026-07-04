package com.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
