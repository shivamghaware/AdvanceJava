package com.jms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jms.entities.Company;
import com.jms.entities.Industry;
import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
	
	public List<Company> findByIndustryAndLocation(Industry industry, String location);
	
	public List<Company> findByName(String name);
}
