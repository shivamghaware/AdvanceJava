package com.healthcare.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.healthcare.dtos.AppointmentDTO;
import com.healthcare.entities.Status;

//DAO Layer test
@DataJpaTest // SC scans only Repository & Entity layer
//By default it begins a transaction for the test method (@Transactional) -> & rolls it back at then @Rollback(true)
//Do not replace the main DB , configured in applicaiton.properties (.yml)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestAppointmentRepository {
	// dependency
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Test // method level annotation to declare a test : JUnit
	void testGetAppointmentDetailsForPatient() {
		List<AppointmentDTO> list = appointmentRepository.getAppointmentDetailsForPatient(5l, Status.SCHEDULED);
		//1st arg - expected 2nd arg - actual
		assertEquals(3, list.size());
	}

}
