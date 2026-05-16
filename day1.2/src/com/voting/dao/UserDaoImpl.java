package com.voting.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.voting.entities.User;
import static com.voting.utils.DBUtils.getConnection;

public class UserDaoImpl implements UserDao {
	
	private Connection connection;
	private PreparedStatement pst1,pst2,pst3,pst4,pst5,pst6,pst7;
	
	
	//default constructor - invoked by above layer ( tester)
	public UserDaoImpl() throws SQLException{
		// 1. get fixed db connection
		connection=getConnection();
		//2. create PreparedStatement to hold parameterized query - sign in
		pst1=connection.prepareStatement("select * from users where email=? and password=?");
		// SO THAT QUERY IS PRECOMPILED AND JUST USED WITH PARAMETERS WHEN REQUESTED
		
		pst2=connection.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		
		
		pst3=connection.prepareStatement("update users set password=? where email=? and password=?");
		
		pst4=connection.prepareStatement("delete from users where id=?");
		
		pst5=connection.prepareStatement("select * from users");
		
		pst6=connection.prepareStatement("update users set status=1 where id=?");
		
		pst7=connection.prepareStatement("update candidates set votes=votes+1 where id=?");
		
		System.out.println("user dao created !");
		
	}

	@Override
	public User autheticateUser(String email, String password) throws SQLException {
		// 1. set IN parameters
		pst1.setString(1, email);
		pst1.setString(2, password);
		//2. exec query -> ResultSet -> process it
		try (ResultSet rst=pst1.executeQuery()) {
			if(rst.next())
			{
				/*
				 * long userId, String firstName, String lastName, String email, String password, Date dob, boolean voted,
			String role
				ORM
				 */
				return new User(rst.getLong(1), rst.getString(2), rst.getString(3), email, password, rst.getDate(6), rst.getBoolean(7), rst.getString(8));
			}
		}
		return null;
	}
	
	
	@Override
	public String signup(User newUser) throws SQLException {
		pst2.setString(1, newUser.getFirstName());
		pst2.setString(2, newUser.getLastName());
		pst2.setString(3, newUser.getEmail());
		pst2.setString(4, newUser.getPassword());
		pst2.setDate(5, newUser.getDob());
		pst2.setBoolean(6, false);
		pst2.setString(7,"voter");
		
        /*
         * 
         * public int executeUpdate() throw SQL EXception*/	
		
		
		int rowCount=pst2.executeUpdate();
		if(rowCount==1) {
			return "User Registered";
		}
		
		return "registration failed";
	}

	@Override
	public String changePassword(String email,String oldpass,String newpass) throws SQLException {
		pst3.setString(1, newpass);
		pst3.setString(2, email);
		pst3.setString(3, oldpass);
		
		int rowCount=pst3.executeUpdate();
		if(rowCount==1) {
			return "Updated Password";
		}
		
		return "Password Not Changed!";
	}

	@Override
	public String deleteUser(long id) throws SQLException {
		// TODO Auto-generated method stub
		pst4.setLong(1, id);
		
		int rowCount=pst4.executeUpdate();
		
		if(rowCount==1) {
			return "User Deleted Successfully!";
		}
			
		return "User Not Deleted!";
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		
		List<User> allusers=new ArrayList<>();
		try(ResultSet rst=pst5.executeQuery()){
			while(rst.next()) {
				allusers.add(new User(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDate(6), rst.getBoolean(7), rst.getString(8)));
			}
		}
		
		return allusers;
	}
	
	

	@Override
	public String updateVotingStatus(long id) throws SQLException {
		pst6.setLong(1, id);
		
		int rowCount=pst6.executeUpdate();
		if(rowCount==1) {
			return "Updated Voting Satus";
		}
		
		return "Not Updated Voting Satus";
	}

	@Override
	public String incrementCandidateVotes(long id) throws SQLException {
		pst7.setLong(1, id);	
		
		int rowCount=pst7.executeUpdate();
		if(rowCount==1) {
			return "Candidate Votes Incremented";
		}
		
		return "Candidates votes Not Incremented";
	}

	@Override
	public void cleanUp() throws SQLException {
		//1 . close PSTs
		if(pst1 != null)
		{
			pst1.close();
		}
		
		if(pst2 != null)
		{
			pst2.close();
		}
		
		if(pst3!=null) {
			pst3.close();
		}
		
		if(pst4!=null) {
			pst4.close();
		}
		
		if(pst5!=null) {
			pst5.close();
		}
		
		if(pst6!=null) {
			pst6.close();
		}
		
		if(pst7!=null) {
			pst7.close();
		}
		
		//2. close cn
		if(connection != null)
		{
			connection.close();
		}	
		System.out.println("user dao cleaned up !");
	}
	

}
