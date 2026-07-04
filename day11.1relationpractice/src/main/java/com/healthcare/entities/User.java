package com.healthcare.entities;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id",column = @Column(name="user_id"))
@ToString(exclude = {"password","imagePath"})
public class User extends BaseEntity {
	
	
	@Column(name = "first_name",length = 30)
	private String firstName;
	
	@Column(name = "last_name",length = 30)
	private String lastName;
	
	@Column(unique = true, length = 50)
	private String email;
	
	@Column(nullable = false, length = 300)
	private String password;
	
	@Column(unique = true, length = 15)
	private String phone;
	
	
	private LocalDate dob;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "reg_amount")
	private Integer regAmount;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public User(String firstName, String lastName, String email, String password,String phone, LocalDate dob, int regAmount,
			UserRole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password=password;
		this.phone = phone;
		this.dob = dob;
		this.regAmount = regAmount;
		this.role = role;
	}
	
}
