package com.ems.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ems.entities.Department;

@Repository //to declare a spring bean containing data access logic
public class DepartmentDaoImpl implements DepartmentDao {
	//depcy 
	@Autowired // match by type 
	private SessionFactory sessionFactory;

	@Override
	public List<Department> getAllDepartments() {
		String jpql="select d from Department d";
		return sessionFactory.getCurrentSession() //Session - db cn + l1 cache
				.createQuery(jpql, Department.class)
				.getResultList();
	}

}
