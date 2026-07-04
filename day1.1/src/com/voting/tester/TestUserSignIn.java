package com.voting.tester;

import java.sql.*;
import java.util.Scanner;

import static com.voting.utils.DBUtils.getConnection;
/*
 * User sign in
i/p - email , password
successful o/p -display user details fetched from DB
failed - error message
 */
public class TestUserSignIn {
	public static void main(String[] args) {
		String sql="select * from users where email=? and password=?";
		try (//sc
				Scanner sc=new Scanner(System.in);
				//cn
				Connection cn=getConnection();
				//pst
				PreparedStatement pst=cn.prepareStatement(sql);			
				) {
			System.out.println("Enter email and password");
			//set IN params
			pst.setString(1, sc.next());//email
			pst.setString(2, sc.next());//pwd
			//exec query , get ResultSet & ResultSet processing
			try (ResultSet rst=pst.executeQuery())
			{
				if(rst.next())
				{
					System.out.println("Login Successful !");
					System.out.printf("User Id %d Name %s %s DoB %s %n",
							rst.getLong(1),rst.getString(2),rst.getString(3),
							rst.getDate(6));
				} else
				{
					System.out.println("Invalid email or password !!!!!");
				}
			} //rst.close
		} //pst.close , cn.close , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
