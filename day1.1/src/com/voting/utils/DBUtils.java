package com.voting.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;
	private static String dbURL = "jdbc:mysql://localhost:3306/voting";
	private static String userName = "root";
	private static String password = "root";
	//add a static method to get fixed DB connection
	public static Connection getConnection() throws SQLException{
		//legacy - load JDBC Type IV driver
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		//get DB connection from Driver Manager
		connection=DriverManager.getConnection(dbURL, userName, password);
		return connection;
		
	}
}
