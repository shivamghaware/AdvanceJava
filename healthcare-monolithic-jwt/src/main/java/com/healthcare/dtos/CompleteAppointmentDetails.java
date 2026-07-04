package com.healthcare.dtos;

import java.time.LocalDateTime;

import com.healthcare.entities.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompleteAppointmentDetails {
	private Long appointmentId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Status status;
	private String docFirstName;
	private String docLastName;
	private String patientFirstName;
	private String patientLastName;
}
