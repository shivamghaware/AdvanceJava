package com.account.dao;

import java.sql.SQLException;

import com.entities.Account;

public interface AccountDao {
	void cleanup() throws SQLException;
	
	Account getById(int id) throws SQLException;

}
