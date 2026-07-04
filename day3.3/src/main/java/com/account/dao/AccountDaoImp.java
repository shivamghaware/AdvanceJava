package com.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.account.utils.DBUtils.getConnection;

import com.entities.Account;

public class AccountDaoImp implements AccountDao {
	private Connection connection;
	private PreparedStatement pst1;

	
	public AccountDaoImp() throws SQLException{
		connection=getConnection();
		pst1=connection.prepareStatement("select * from accounts where id=?");
	}

	@Override
	public Account getById(int id) throws SQLException {
		pst1.setInt(1, id);
	
		try(ResultSet rst=pst1.executeQuery()){
			if(rst.next()) {
				return new Account(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
			}
		}
		return null;
	}
	
	@Override
	public void cleanup() throws SQLException{
		if(pst1!=null) {
			pst1.close();
		}
		if(connection!=null) {
			connection.close();
		}

	}
}
