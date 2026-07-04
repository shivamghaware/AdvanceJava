package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.UserRole;

public class GetLastNamesByRole {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			UserDao userDao=new UserDaoImpl();
			//2. accept user role
			System.out.println("Enter user role");
				//invoke dao's method
			userDao.getLastNamesByRole(UserRole.valueOf(sc.next().toUpperCase())).forEach(System.out::println);
		} //JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
