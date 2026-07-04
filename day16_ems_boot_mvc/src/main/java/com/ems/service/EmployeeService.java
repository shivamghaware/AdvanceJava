package com.ems.service;

import java.util.List;

import com.ems.entities.Employee;

public interface EmployeeService {
	List<Employee> getEmpsByDepartmentId(Long deptId);

	String deleteEmpDetails(Long empId);

	Employee getEmpDetailsById(Long empId);

	String updateEmpDetails(Long empId, Employee emp);
}
