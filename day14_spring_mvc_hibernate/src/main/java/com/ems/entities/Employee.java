package com.ems.entities;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA annotations
@Entity
@Table(name="emps")
@AttributeOverride(name="id",column = @Column(name="emp_id"))
//lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude ="myDepartment")
public class Employee extends BaseEntity {
	//email, first_name, last_name, join_date, password, salary, type, dept_id
	@Column(unique = true, length = 50)
	private String email;
	@Column(nullable = false, length = 500)
	private String password;
	@Column(name="first_name",length = 30)
	private String firstName;
	@Column(name="last_name",length = 30)
	private String lastName;
	@Column(name="join_date")
	private LocalDateTime joinDate;
	private int salary;
	@Enumerated(EnumType.STRING)
	@Column(name="emp_type")
	private EmploymentType empType;
	//Employee m------>1 Department
	@ManyToOne
	@JoinColumn(name="dept_id",nullable = false)
	private Department myDepartment;	
}
