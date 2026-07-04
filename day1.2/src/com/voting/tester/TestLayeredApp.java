package com.voting.tester;

import java.util.Scanner;

import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

public class TestLayeredApp {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			//create user dao instance(dependent(tester) creating dependecy(User dao))
			UserDaoImpl userDao=new UserDaoImpl(); //init phase
			System.out.println("Enter email & password");
			// invoke dao's method - service phase
			User autheticatedUser = userDao.autheticateUser(sc.next(), sc.next());
			//null checking
			if(autheticatedUser != null)
			{
				System.out.println("Successful Login ");
				System.out.println("User details "+autheticatedUser);
			}
			else 
			{
				System.out.println("Invalid email or password !!!!!!!!");
			}
			//clean up
			userDao.cleanUp();//destroy phase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
