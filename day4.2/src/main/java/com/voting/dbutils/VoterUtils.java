package com.voting.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VoterUtils {
	private static String dburl="jdbc:mysql://localhost:3306/jdbc";
	private static String username="root";
	private static String password="shivam12345";
	
	public static Connection getSqlConnection() throws SQLException{
		return DriverManager.getConnection(dburl, username, password);
	}
}
