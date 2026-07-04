package com.voting.dao;

import java.sql.*;

import com.voting.entities.User;
import static com.voting.utils.DBUtils.getConnection;

public class UserDaoImpl implements UserDao {
	private Connection connection;
	private PreparedStatement pst1, pst2, pst3, pst4;

	// default constructor - invoked by above layer ( tester)
	public UserDaoImpl(String dbURL,String userName,String password) throws SQLException {
		// 1. get fixed db connection
		connection = getConnection(dbURL,userName,password);
		// 2. create PreparedStatement to hold parameterized query - sign in
		pst1 = connection.prepareStatement("select * from users where email=? and password=?");
		// 2. create PreparedStatement to hold parameterized query - sign up
		pst2 = connection.prepareStatement(
				"insert into users (first_name , last_name , email , password , dob,status,role) values (?,?,?,?,?,?,?)");
		pst3 = connection.prepareStatement("update users set password=? where email=? and password=?");
		// update
		pst4 = connection.prepareStatement("update users set status=1 where id=?");
		System.out.println("user dao created !");

	}

	@Override
	public User autheticateUser(String email, String password) throws SQLException {
		// 1. set IN parameters
		pst1.setString(1, email);
		pst1.setString(2, password);
		// 2. exec query -> ResultSet -> process it
		try (ResultSet rst = pst1.executeQuery()) {
			// rst cursor - before the 1st row
			if (rst.next()) {
				/*
				 * long userId, String firstName, String lastName, String email, String
				 * password, Date dob, boolean voted, String role
				 */
				// ORM
				return new User(rst.getLong(1), rst.getString(2), rst.getString(3), email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
			}
		}
		return null;
	}

	@Override
	public String signUp(User newUser) throws SQLException {
		// 1. set IN params
		pst2.setString(1, newUser.getFirstName());// first name
		pst2.setString(2, newUser.getLastName());// last name
		pst2.setString(3, newUser.getEmail());// email
		pst2.setString(4, newUser.getPassword());// password
		pst2.setDate(5, newUser.getDob());// dob
		pst2.setBoolean(6, false);
		pst2.setString(7, "voter");
		// 2. exec DML -
		/*
		 * Method of PreparedStatement public int executeUpdate() throws SQLException
		 */
		int rowCount = pst2.executeUpdate();
		if (rowCount == 1)
			return "User registered !";
		return "User registration failed!!!!";
	}

	@Override
	public void cleanUp() throws SQLException {
		// 1 . close PSTs
		if (pst1 != null) {
			pst1.close();
		}
		if (pst2 != null) {
			pst2.close();
		}
		if (pst3 != null) {
			pst3.close();
		}
		if (pst4 != null) {
			pst4.close();
		}
		// 2. close cn
		if (connection != null) {
			connection.close();
		}
		System.out.println("user dao cleaned up !");
	}

	public String changePassword(String email, String oldPass, String newPass) throws SQLException {
		// set IN params
		pst3.setString(1, newPass);
		pst3.setString(2, email);
		pst3.setString(3, oldPass);
		/*
		 * Method of PreparedStatement public int executeUpdate() throws SQLException
		 */
		int rowCount = pst3.executeUpdate();
		if (rowCount == 1)
			return "User password updated!";
		return "Updation failed!!!!";

	}

	@Override
	public String updateVotingStatus(long voterId) throws SQLException {
		// set In param - voter id
		pst4.setLong(1, voterId);
		int updateCount = pst4.executeUpdate();
		if (updateCount == 1)
			return "Voting status updated ....";
		return "status updation failed !!!!";
	}

}
