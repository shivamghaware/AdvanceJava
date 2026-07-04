package com.healthcare.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@ToString(callSuper = true,exclude = {"userDetails","appointments"})
public class Doctor extends BaseEntity {
	private String qualifications;
	private String speciality;
	@Column(name="experience_in_years")
	private int experienceInYears;
	@Column(name="appointment_time")
	private int appointmentTime;
	private int fees;
	//Doctor 1--->1 UserDetails 
	//cascade all operations from Doctor -> UserDetails
	@OneToOne (cascade = CascadeType.ALL)
	//mandatory 
	@JoinColumn(name="doctor_id",nullable = false)
	@MapsId //shared PK + FK
	private UserDetails userDetails;
	// Doctor 1---->* Appointment
	@OneToMany(mappedBy = "myDoctor")
	private List<Appointment> appointments=new ArrayList<>();
	public Doctor(String qualifications, String speciality, int experienceInYears, int appointmentTime, int fees) {
		super();
		this.qualifications = qualifications;
		this.speciality = speciality;
		this.experienceInYears = experienceInYears;
		this.appointmentTime = appointmentTime;
		this.fees = fees;
	}
	
}
