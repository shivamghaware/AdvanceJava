package com.voting.dao;

import java.sql.SQLException;

import com.voting.entites.User;

public interface UserDao extends BaseDao{
	User authenticateUser(String email,String password) throws SQLException;
	
	String updateVotingStatus(long id)throws SQLException;
}
