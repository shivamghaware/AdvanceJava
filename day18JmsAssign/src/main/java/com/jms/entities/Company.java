package com.jms.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "companies")
@AttributeOverride(name = "id" ,column = @Column(name="company_id"))
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {
	
	@Column(length = 50 ,unique = true, nullable = false)
	private String name;
	
	@Column(length = 50,unique = true, nullable = false)
	private String email;
	
	@Column(length = 100)
	private String location;
	
	@Enumerated(EnumType.STRING)
	private Industry industry;
}
