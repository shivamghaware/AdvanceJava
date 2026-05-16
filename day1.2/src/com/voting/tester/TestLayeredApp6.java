package com.voting.tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.voting.dao.UserDaoImpl;
import com.voting.entities.User;

public class TestLayeredApp6 {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			//create user dao instance(dependent(tester) creating dependecy(User dao))
			UserDaoImpl userDao=new UserDaoImpl(); //init phase
			System.out.println("Enter ID who's VotingStatus needs to Be Updates");
			// invoke dao's method - service phase
			
			System.out.println("Voting : "+userDao.updateVotingStatus(sc.nextLong()));
			
			//clean up
			userDao.cleanUp();//destroy phase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
