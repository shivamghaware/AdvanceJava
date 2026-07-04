package com.ems.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA annotations
@Entity
@Table(name="departments")
@AttributeOverride(name="id",column = @Column(name="dept_id"))
//lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Department extends BaseEntity {
	@Column(length = 50, name="dept_name",unique = true)
	private String name;
	@Column(length = 100)
	private String location;
}
