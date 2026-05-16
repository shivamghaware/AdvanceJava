package com.voting.tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

public class TestLayeredApp5 {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			//create user dao instance(dependent(tester) creating dependecy(User dao))
			UserDaoImpl userDao=new UserDaoImpl(); //init phase
			System.out.println("All Users are :");
			// invoke dao's method - service phase
			
			List <User> alluser=userDao.getAllUsers();
			alluser.stream().forEach(System.out::println);
			
			//clean up
			userDao.cleanUp();//destroy phase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
