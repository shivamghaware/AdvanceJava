package com.healthcare.tester;
import org.hibernate.*;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class RegisterNewUser {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			UserDao userDao=new UserDaoImpl();
			//2. accept user details - user
			System.out.println("""
					Enter user details  - 
					 firstName,  lastName,  dob,  email,  password,  phone,
			 regAmount,  role
					""");
			User user=new User(sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.next(), sc.next(), sc.next(), sc.nextInt(), UserRole.valueOf(sc.next().toUpperCase())); //transient user- not yet persistent
			//invoke dao's method
			System.out.println(userDao.registerUser(user));
		} //JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
