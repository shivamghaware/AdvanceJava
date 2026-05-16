package com.voting.tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

public class TestLayeredApp7 {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			//create user dao instance(dependent(tester) creating dependecy(User dao))
			UserDaoImpl userDao=new UserDaoImpl(); //init phase
			System.out.println("Enter ID of Candidate you want to Vote");
			// invoke dao's method - service phase
			
			System.out.println("Increment Vote Count Status: "+userDao.incrementCandidateVotes(sc.nextLong()));
			
			//clean up
			userDao.cleanUp();//destroy phase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
