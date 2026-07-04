package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.entities.Department;
import com.ems.repository.DepartmentRepository;

@Service //To declare a spring bean containing B.L
@Transactional //for auto tx management
public class DepartmentServiceImpl implements DepartmentService {
	//depcy
	@Autowired //Filed level D.I
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

}
