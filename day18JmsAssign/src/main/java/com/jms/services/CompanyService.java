package com.jms.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jms.entities.Industry;
import com.jms.exception.ResourceNotFound;
import com.jms.repo.CompanyRepo;
import com.jms.entities.ApiResponseDTO;
import com.jms.entities.Company;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {
	private final CompanyRepo repo;
//	private final ModelMapper mapper;
	
	
	public List<Company> getAllCompaniesByIndustryLocation(Industry industry, String location) {
		
		return repo.findByIndustryAndLocation(industry, location);
		
	}

}
