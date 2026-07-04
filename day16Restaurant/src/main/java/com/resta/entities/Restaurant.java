package com.resta.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name="restaurant_id"))
@Getter
@Setter
@ToString
public class Restaurant extends BaseEntity{
	
	@Column(length = 100)
	private String address;
	
	@Column(length = 30)
	private String city;
	
	@Column(length = 100)
	private String description;
	
	@Column(length = 30 , nullable = false)
	private String name;
	
	private boolean status;

	public Restaurant(String address, String city, String description, String name, boolean status) {
		super();
		this.address = address;
		this.city = city;
		this.description = description;
		this.name = name;
		this.status = status;
	}
}
