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
//JPA annotations
@Entity //class level annotation to declare entity class
@Table(name="users")//to specify table name
/*
 * To replace the column name inherited from base class
 * - @AttributeOverride
 * name - field name in the base class
 * column - col name in the users table
 */
@AttributeOverride(name = "id", column = @Column(name="user_id"))
//lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password","imagePath"})
public class UserDetails extends BaseEntity{
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
	
	public UserDetails(String firstName, String lastName, LocalDate dob, String email, String password, String phone,
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
