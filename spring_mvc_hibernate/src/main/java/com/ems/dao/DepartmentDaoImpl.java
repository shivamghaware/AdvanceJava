package com.ems.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ems.entities.Department;

public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Department> getDepartments() {
		String jpql="select d from departments d";
		
		return sessionFactory.getCurrentSession().createQuery(jpql,Department.class).getResultList();
	}

}
