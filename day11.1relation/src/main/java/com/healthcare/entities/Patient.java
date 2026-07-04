package com.healthcare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="patients")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = "userDetails")
public class Patient extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(name="blood_group")
	private BloodGroup bloodGroup;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name="family_history",length = 500)
	private String familyHistory;
	//Establish Patient 1---->1 UserDetails
	@OneToOne
	@JoinColumn(name="patient_id",nullable = false)
	@MapsId //shared PK with users table + FK
	private UserDetails userDetails;	

}
