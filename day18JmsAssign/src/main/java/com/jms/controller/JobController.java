package com.jms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jms.entities.Industry;
import com.jms.entities.JobRequestDTO;
import com.jms.entities.JobType;
import com.jms.services.JobService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Validated
public class JobController {

	private final JobService jobService;

	@PostMapping("/postNewJob")
	public ResponseEntity<?> postNewJob(@RequestBody @Valid JobRequestDTO jobRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(jobService.postNewJob(jobRequest));
	}

	@GetMapping("/all/{location}")
	public ResponseEntity<?> getAllJobsByLocation(@PathVariable @NotBlank String location) {
		return ResponseEntity.ok(jobService.getAllJobsByLocation(location));
	}

	@DeleteMapping("remove/{jobType}/{companyName}")
	public ResponseEntity<?> getAllJobsByLocation(@PathVariable @NotNull JobType jobType,
			@PathVariable @NotBlank String companyName) {
		return ResponseEntity.status(HttpStatus.OK).body(jobService.deleteByJobAndLocation(jobType, companyName));
	}

	@PutMapping("/update")
	public ResponseEntity<?> UpdateByJobTitleCompanyid(@RequestParam Long companyId,@RequestParam String jobTitle ,@RequestParam double newSal){
		return ResponseEntity.status(HttpStatus.OK).body(jobService.UpdateByJobTitleCompanyid(companyId,jobTitle,newSal));
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> findByIndustryMinsal(@RequestParam Industry industry,@RequestParam double minSal){
		return ResponseEntity.status(HttpStatus.OK).body(jobService.findByIndustryMinsal(industry,minSal));
	}
	
	@PutMapping("/statuschange")
	public ResponseEntity<?> statuschange(@RequestParam String companyName,@RequestParam String jobTitle){
		return ResponseEntity.status(HttpStatus.OK).body(jobService.statuschange(companyName,jobTitle));
	}
}
