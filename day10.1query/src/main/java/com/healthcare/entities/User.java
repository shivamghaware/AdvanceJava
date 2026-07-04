package com.healthcare.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//JPA annotations
@Entity //class level annotation to declare entity class
@Table(name="users")//to specify table name
//lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password","imagePath"})
public class User {
	@Id //PK constraint
	@GeneratedValue(strategy = GenerationType.IDENTITY) //DB - auto generations of ids using auto increment constraint
	//to specify column name
	@Column(name="user_id")
	/*
	 * Hibernate recommends (Gavin King)
	 * to add PK data type as wrapper
	 * Since hibernate can perform null checking (vs zero value)efficiently  to identify transient vs persistent entities 
	 * 
	 */
	private Long userId;
	@Column(name="created_on")
	//Hibernate auto generates creation date , when the entity is created first
	@CreationTimestamp
	private LocalDate createdOn;
	@Column(name="last_updated")
	//Hibernate auto creates updated time stamp , whenever entity is updated
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	@Column(name="first_name",length = 30) //varchar(30)
	private String firstName;
	@Column(name="last_name",length = 30) //varchar(30)
	private String lastName;
	private LocalDate dob;
	@Column(length = 50,unique = true)//adds UNIQUE constraint
	private String email;
	@Column(length = 300,nullable = false)//NOT NULL constraint
	private String password;
	@Column(length = 14,unique = true)
	private String phone;
	@Column(name="reg_amount")
	private Integer regAmount;//can be replaced by int
	@Enumerated(EnumType.STRING) //col type - ENUM | varchar
	private UserRole role;
	@Column(name="image_path")
	private String imagePath;
	
	public User(String firstName, String lastName, LocalDate dob, String email, String password, String phone,
			Integer regAmount, UserRole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.regAmount = regAmount;
		this.role = role;
	}
	
}
