package com.voting.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	//add a static method to get fixed DB connection
	public static Connection getConnection(String dbURL,String userName,String password) throws SQLException{
		//legacy - load JDBC Type IV driver
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		//get DB connection from Driver Manager
		return DriverManager.getConnection(dbURL, userName, password);
		
		
	}
}
