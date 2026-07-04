package com.healthcare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.BookAppointment;

@SpringBootTest //SC scans all the components - controller , service & repos
class TestAppointmentService {
	@Autowired
	private AppointmentService appService;

	@Test
	void testBookAppointment() {
		BookAppointment dto=new BookAppointment(16l, 2l, LocalDateTime.parse("2026-08-22T15:00"));
	//	ApiResponse response = appService.bookAppointment(dto);
	//	assertEquals(true, response.getStatus().equals("Success"));
		ResourceNotFoundException exc = assertThrows(ResourceNotFoundException.class, () -> appService.bookAppointment(dto));
		assertEquals(true, exc.getMessage().contains("Invalid Patient ID"));
	}

}
