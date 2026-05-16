package com.voting.tester;

import java.sql.Date;
import java.util.Scanner;

import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

public class TestLayeredApp2 {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			//create user dao instance(dependent(tester) creating dependecy(User dao))
			UserDaoImpl userDao=new UserDaoImpl(); //init phase
			System.out.println("Enter  first_name | last_name | email | password | dob");
			// invoke dao's method - service phase
			User user=new User(sc.next(), sc.next(), sc.next(), sc.next(), Date.valueOf(sc.next()));
			
			System.out.println("Reg Status: "+userDao.signup(user));
			
			//clean up
			userDao.cleanUp();//destroy phase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
