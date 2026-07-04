package com.healthcare.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//JPA
@Entity
@Table(name = "diag_tests")
@AttributeOverride(name = "id", column = @Column(name = "diag_test_id"))
//Lombok
@NoArgsConstructor
@Getter
@Setter
public class DiagTest extends BaseEntity {
	@Column(unique = true)
	private String name;
	private String description;
	private int cost;
	//Establish DiagTest *-----> * Appointment
	@ManyToMany //mandatory
	//optional annotation for further customization
	@JoinTable(name="appointment_tests"
	,joinColumns = @JoinColumn(name="test_id"),
	inverseJoinColumns = @JoinColumn(name="appointment_id"))
	private Set<Appointment> appointments=new HashSet<>();
	public DiagTest(String name, String description, int cost) {
		super();
		this.name = name;
		this.description=description;
		this.cost = cost;
	}	
	
}
