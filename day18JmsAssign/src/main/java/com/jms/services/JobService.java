package com.jms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.jms.entities.ApiResponseDTO;
import com.jms.entities.Company;
import com.jms.entities.Industry;
import com.jms.entities.Job;
import com.jms.entities.JobRequestDTO;
import com.jms.entities.JobType;
import com.jms.entities.Status;
import com.jms.exception.ResourceNotFound; // Used this standard exception naming
import com.jms.repo.CompanyRepo;
import com.jms.repo.JobRepo; // 👈 Added missing import

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JobService {
    
    private final JobRepo repo;          // Automatically wired via @RequiredArgsConstructor
    private final CompanyRepo comrepo;    // Automatically wired via @RequiredArgsConstructor

    public Job postNewJob(JobRequestDTO jobRequest) {
        
        // Swapped the misspelled exception with your existing ResourceNotFound exception
        Company company = comrepo.findById(jobRequest.getCompanyid())
                .orElseThrow(() -> new ResourceNotFound("Company Not Found with ID: " + jobRequest.getCompanyid()));
        
        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setSalary(jobRequest.getSalary());
        job.setLocation(jobRequest.getLocation());
        job.setJobType(jobRequest.getJobType());
        
        // This links the managed entity cleanly even with FetchType.LAZY
        job.setCompany(company); 
        
        return repo.save(job);
    }

	public List<Job> getAllJobsByLocation(String location) {
		// TODO Auto-generated method stub
		return repo.findByLocation(location);
		
	}

	public String deleteByJobAndLocation(JobType jobType,String companyName) {
		List<Job> tbdjobs=repo.findByJobTypeAndCompanyName(jobType, companyName);
			
		repo.deleteAllInBatch(tbdjobs);
		
		return "Deleted successfully";
	}

	public String UpdateByJobTitleCompanyid(Long companyId, String jobTitle, double newSal) {
		
		List<Job> tbujobs=repo.findByCompanyIdAndTitle(companyId, jobTitle);
		
		if(tbujobs.size()==0) {
			throw new ResourceNotFound("No Jobs To Update!");
		}
		
		for(Job j:tbujobs) {
			j.setSalary(newSal);
		}
		return "updated sal";
	}

	public List<Job> findByIndustryMinsal(Industry industry, double minSal) {
		List<Job> jobs=repo.findBySalaryGreaterThanEqualAndCompanyIndustry( minSal,industry);
		
		return jobs;
	}

	public String statuschange(String companyName, String jobTitle) {
		List<Job> jobsTBU=repo.findByCompanyNameAndTitle(companyName, jobTitle);
		
		if(jobsTBU.size()==0) {
			throw new ResourceNotFound("No Jobs To Update!");
		}
		
		for(Job j:jobsTBU) {
			j.setStatus(Status.UNAVAILABLE);
		}
		return "Done";
	}
}