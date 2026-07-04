package com.healthcare.tester;
import org.hibernate.*;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class GetUserDetailsById {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			UserDao userDao=new UserDaoImpl();
			//2. accept user id
			System.out.println("""
					Enter user id
					 
					""");
				//invoke dao's method
			System.out.println(userDao.getUserDetails(sc.nextLong()));
		} //JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
