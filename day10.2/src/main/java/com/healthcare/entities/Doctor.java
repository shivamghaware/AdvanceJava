package com.healthcare.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA 
@Entity
@Table(name="doctors")

//Lombok
@NoArgsConstructor
@Getter
@Setter
/*
 * Project Tip (Gavin King)
 * Exclude association fields from toString as well as constructor
 */
@ToString(callSuper = true,exclude = "userDetails")
public class Doctor extends BaseEntity {
	private String qualifications;
	private String speciality;
	@Column(name="experience_in_years")
	private int experienceInYears;
	@Column(name="appointment_time")
	private int appointmentTime;
	private int fees;
	//Doctor 1--->1 UserDetails 
	@OneToOne //mandatory 
	@JoinColumn(name="doctor_id",nullable = false)
	@MapsId
	private UserDetails userDetails;
	public Doctor(String qualifications, String speciality, int experienceInYears, int appointmentTime, int fees) {
		super();
		this.qualifications = qualifications;
		this.speciality = speciality;
		this.experienceInYears = experienceInYears;
		this.appointmentTime = appointmentTime;
		this.fees = fees;
	}
	
}
