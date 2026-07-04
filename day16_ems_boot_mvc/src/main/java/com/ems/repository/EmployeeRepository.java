package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//add derived query method to find existing(sts=true) emps from specified dept id
	List<Employee> findByMyDepartmentIdAndStatusTrue(Long deptId);
}
