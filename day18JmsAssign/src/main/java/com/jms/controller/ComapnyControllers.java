package com.jms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jms.entities.Company;
import com.jms.entities.Industry;
import com.jms.exception.ResourceNotFound;
import com.jms.services.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compaines")
public class ComapnyControllers {
	private final CompanyService companyService;

	@GetMapping("/{industry}/{location}")
	ResponseEntity<?> getCompanyListByTypeAndLocation(@PathVariable Industry industry, @PathVariable String location) {
		List<Company> companies = companyService.getAllCompaniesByIndustryLocation(industry, location);
		if(companies.size()==0) throw new ResourceNotFound("Nothing to Display");
		return ResponseEntity.ok(companies);
	}
}
