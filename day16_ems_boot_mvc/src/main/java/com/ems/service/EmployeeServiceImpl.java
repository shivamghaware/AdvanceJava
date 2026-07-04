package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.custom_exceptions.ResourceNotFoundException;
import com.ems.entities.Employee;
import com.ems.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//depcy 
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> getEmpsByDepartmentId(Long deptId) {
		// TODO Auto-generated method stub
		return empRepo.findByMyDepartmentIdAndStatusTrue(deptId);
	}

	@Override
	public String deleteEmpDetails(Long empId) {
		// 1. get emp details by id
		Employee emp=empRepo.findById(empId) //Optional<Employee>
				.orElseThrow(() -> 
				new ResourceNotFoundException("Invalid emp id !!!"));
		//emp - persistent
		emp.setStatus(false);
		return "Soft deleted emp details !!!!!!!!!!";
	}
	/*
	 * success -> Spring Tx mgr -> tx.commit() 
	 * -> session.flush() -> auto dirty chking -> DML - update
	 * -> session.close()
	 */

	@Override
	public Employee getEmpDetailsById(Long empId) {
		
		return empRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Emp id invalid !!!!"));
	}

	@Override
	public String updateEmpDetails(Long empId, Employee emp) {
		Employee existingEmp=getEmpDetailsById(empId);
		//existingEmp - persistent
		existingEmp.setPassword(emp.getPassword());
		existingEmp.setJoinDate(emp.getJoinDate());
		existingEmp.setSalary(emp.getSalary());
		existingEmp.setEmpType(emp.getEmpType());
		return "Emp details updated...";
	}
	
	
	
	

}
