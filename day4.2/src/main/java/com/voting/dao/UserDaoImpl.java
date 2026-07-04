package com.voting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.voting.dbutils.VoterUtils;
import com.voting.entites.User;

public class UserDaoImpl implements UserDao{
	Connection connection;
	PreparedStatement authenticatePST,updateVoteStatusPST;
	
	public UserDaoImpl() throws SQLException{
		connection=VoterUtils.getSqlConnection();
		
		authenticatePST=connection.prepareStatement("select * from users where email=? and password=?");
		
		updateVoteStatusPST=connection.prepareStatement("update users set status=1 where id=?");
	}

	@Override
	public void cleanup() throws SQLException {
		if(authenticatePST!=null)	{
			authenticatePST.close();
		}
		if(updateVoteStatusPST!=null)	{
			updateVoteStatusPST.close();
		}
		if(connection!=null) {
			connection.close();
		}
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		authenticatePST.setString(1, email);
		authenticatePST.setString(2, password);
		
		try(ResultSet rst=authenticatePST.executeQuery()){
			if(rst.next()) {
				return new User(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDate(6), rst.getBoolean(7), rst.getString(8));
			}
		}
		return null;
	}

	@Override
	public String updateVotingStatus(long id) throws SQLException {
		updateVoteStatusPST.setLong(1, id);
		
		int rowCount=updateVoteStatusPST.executeUpdate();
		if(rowCount==1) {
			return "Voted";
		}
		return "NotVoted";
	}

}
