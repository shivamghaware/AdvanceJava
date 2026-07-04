package com.healthcare.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*JPA annotation 
 * - to declare super class for all other entities
 * - no table
 * - common fields *  
 */
@MappedSuperclass
//lombok annotation
@Getter
@Setter
@ToString
public class BaseEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "created_on")
		@CreationTimestamp
	private LocalDate createdOn;
	@Column(name = "last_updated")
		@UpdateTimestamp
	private LocalDateTime lastUpdated;

}
