package com.voting.tester;
import java.sql.*;
import static com.voting.utils.DBUtils.getConnection;
public class TestDBConnection {

	public static void main(String[] args) {
		try(Connection cn=getConnection()) {
			System.out.println("DB connection established - "+cn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
