package com.jms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.jms.entities.Job;
import com.jms.entities.JobType;

import jakarta.validation.constraints.NotBlank;
import com.jms.entities.Company;
import com.jms.entities.Industry;


public interface JobRepo extends JpaRepository<Job,Long> {

	List<Job> findByLocation(String location);
	
	List<Job> findByJobTypeAndCompanyName(JobType jobType,String companyName);
	
	List<Job> findByCompanyIdAndTitle(Long companyId, String title);
	
	List<Job> findBySalaryGreaterThanEqualAndCompanyIndustry(double salary,Industry industry);
	
	List<Job> findByCompanyNameAndTitle(String companyName,String JobTitle);

}
