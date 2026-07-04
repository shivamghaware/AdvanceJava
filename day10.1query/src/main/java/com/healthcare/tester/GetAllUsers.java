package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;

public class GetAllUsers {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();) {
			// 1. create dao instance
			UserDao userDao = new UserDaoImpl();			
			userDao.getAllUserDetails()
			.forEach(System.out::println);
		} // JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
