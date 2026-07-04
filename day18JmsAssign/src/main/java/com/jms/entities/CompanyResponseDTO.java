package com.jms.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CompanyResponseDTO {
	
	private Long id;
	private LocalDate createdAt;
	
	private LocalDateTime lastUpdated;

	private String name;
	
	private String email;
	
	private String location;
	private Industry industry;
}
