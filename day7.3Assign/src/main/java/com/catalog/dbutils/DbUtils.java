package com.catalog.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
	public static Connection getConnection(String dbURL,String userName,String password) throws SQLException {
		return DriverManager.getConnection(dbURL, userName, password);
	}
}
