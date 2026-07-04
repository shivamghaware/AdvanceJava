package com.healthcare.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctors")
@NoArgsConstructor
@Getter
@Setter
//@ToString(callSuper = true,exclude = {"user"})
@ToString(callSuper = true)
public class Doctor extends BaseEntity{
	@Column(name="appointment_time")
	private int appointmentTime;
	
	@Column(name="experience_in_years")
	private int experienceInYears;
	
	private int fees;
	
	@Column(length = 255)
	private String qualification;
	
	@Column(length = 255)
	private String speciality;
	
	@OneToOne
	@JoinColumn(name = "doctor_id",nullable = false)
	@MapsId
	private User user;

	public Doctor(int appointmentTime, int experienceInYears, int fees, String qualification, String speciality) {
		super();
		this.appointmentTime = appointmentTime;
		this.experienceInYears = experienceInYears;
		this.fees = fees;
		this.qualification = qualification;
		this.speciality = speciality;
	}
	
}
