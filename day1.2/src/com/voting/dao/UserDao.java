package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.entities.User;

public interface UserDao extends BaseDao{
//add a method for user sign in
	User autheticateUser(String email,String password) throws SQLException;
	
	// addded method signup user
	String signup(User newUser)throws SQLException;
	
	//change password
	String changePassword(String email,String oldpass,String newpass)throws SQLException;
	
	String deleteUser(long id)throws SQLException;
	
	List<User> getAllUsers()throws SQLException;
	
	String updateVotingStatus(long id) throws SQLException;
	
	String incrementCandidateVotes(long id) throws SQLException;
	
	
}
