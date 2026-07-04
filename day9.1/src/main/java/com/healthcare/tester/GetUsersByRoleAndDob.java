package com.healthcare.tester;
import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.UserRole;

public class GetUsersByRoleAndDob {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			UserDao userDao=new UserDaoImpl();
			
			System.out.println("""
					Enter user role & date				 
					""");
				//invoke dao's method
			userDao.getUsersByRoleAndDate(UserRole.valueOf(sc.next().toUpperCase()),LocalDate.parse(sc.next()))
			.forEach(System.out::println);
		} //JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
