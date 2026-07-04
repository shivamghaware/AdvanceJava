package com.voting.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static String dbURL = "jdbc:mysql://localhost:3306/jdbc";
	private static String userName = "root";
	private static String password = "shivam12345";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbURL, userName, password);
	}
}
