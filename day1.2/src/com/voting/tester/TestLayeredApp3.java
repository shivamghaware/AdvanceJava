package com.voting.tester;

import java.sql.Date;
import java.util.Scanner;

import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

public class TestLayeredApp3 {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			//create user dao instance(dependent(tester) creating dependecy(User dao))
			UserDaoImpl userDao=new UserDaoImpl(); //init phase
			System.out.println("Enter email | oldpass | newpass");
			// invoke dao's method - service phase
			
			System.out.println("Reg Status: "+userDao.changePassword(sc.next(),sc.next(),sc.next()));
			
			//clean up
			userDao.cleanUp();//destroy phase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
