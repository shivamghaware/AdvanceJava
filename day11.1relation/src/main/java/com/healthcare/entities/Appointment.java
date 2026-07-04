package com.healthcare.entities;
/*
 * appointments 
Table columns - appointment_id , creation date , last updated ,doctor_id , patient _id , appointment start time , end time , status
 */

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA 
@Entity
@Table(name = "appointments")
@AttributeOverride(name = "id", column = @Column(name = "appointment_id"))
//Lombok
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = { "myDoctor", "myPatient" })
@EqualsAndHashCode(of="startTime",callSuper = false)
public class Appointment extends BaseEntity {
	@Column(name = "start_time")
	private LocalDateTime startTime;
	@Column(name = "end_time")
	private LocalDateTime endTime;
	@Enumerated(EnumType.STRING)
	private Status status = Status.SCHDULED;
	// Appointment *----->1 Doctor
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor myDoctor;
	// Appointment *----->1 Patient
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient myPatient;

	public Appointment(LocalDateTime startTime) {
		super();
		this.startTime = startTime;
	}

}
