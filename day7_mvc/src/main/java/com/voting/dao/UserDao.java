package com.voting.dao;

import java.sql.SQLException;

import com.voting.entities.User;

public interface UserDao extends BaseDao {
//add a method for user sign in
	User autheticateUser(String email, String password) throws SQLException;

	// add a method for user sign up
	String signUp(User newUser) throws SQLException;

	// add a method to change password
	String changePassword(String email, String oldPass, String newPass) throws SQLException;
	
	//update voting status
	String updateVotingStatus(long voterId) throws SQLException;
}
