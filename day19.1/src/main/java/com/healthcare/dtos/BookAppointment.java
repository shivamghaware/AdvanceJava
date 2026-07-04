package com.healthcare.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookAppointment {
	private Long patientId;
	private Long doctorId;
	private LocalDateTime start;
}
