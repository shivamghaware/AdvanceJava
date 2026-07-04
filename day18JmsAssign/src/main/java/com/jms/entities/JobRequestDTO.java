package com.jms.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class JobRequestDTO {
	// company id , title , description ,salary,location, jobType
	
	@Min(0)
	private Long companyid;
	
	@NotBlank(message = "Enter Valid Title")
	private String title;
	
	@NotBlank
	private String description;
	
	@Positive
	private double salary;
	
	@NotBlank
	private String location;
	
	@NotNull
	private JobType jobType;	
}
