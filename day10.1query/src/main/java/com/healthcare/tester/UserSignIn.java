package com.healthcare.tester;
import static com.healthcare.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;

public class UserSignIn {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			UserDao userDao=new UserDaoImpl();
			//2. accept user id
			System.out.println("Enter email & password");
				//invoke dao's method
			System.out.println(userDao.authenticate(sc.next(),sc.next()));
		} //JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
