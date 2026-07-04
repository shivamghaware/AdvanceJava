package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
