package com.healthcare.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.healthcare.entities.Status;

//to declare a test class for Repository layer - scans only Repository & Entities
@DataJpaTest 
//Run test cases on the main db of the application configured in application.properties
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestIsDoctorUnAvailable {
	@Autowired
	private AppointmentRepository appRepo;

	@Test //JUnit annotation to declare test method
	void testIsDoctorUnavailable() {
		/*
		 * Long docId,Status sts,LocalDateTime end, LocalDateTime start
		 */
		boolean flag = appRepo.existsByMyDoctorIdAndStatusAndStartDateTimeBeforeAndEndDateTimeAfter(2l,Status.SCHEDULED,LocalDateTime.parse("2026-06-20T10:30"),
				LocalDateTime.parse("2026-06-20T10:10"));
		assertEquals(true, flag);
		
	}

}
