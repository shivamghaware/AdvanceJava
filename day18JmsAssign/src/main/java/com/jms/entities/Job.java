package com.jms.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobs")
@AttributeOverride(name = "id" ,column = @Column(name="job_id"))
@Getter
@Setter
@ToString(exclude = "company")
@NoArgsConstructor
@AllArgsConstructor
public class Job extends BaseEntity{
	
	@Column(length = 50,nullable = false,unique = true)
	private String title;
	

	@Column(length = 100)
	private String description;
	
	private double salary;
	
	@Column(length = 100)
	private String location;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "job_type")
	private JobType jobType;
	
	@CurrentTimestamp
	@Column(name = "posted_date")
	private LocalDate postedDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status = Status.AVAILABLE;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id",nullable = false)
	@JsonIgnore
	private Company company;
}
