package com.voting.dao;

import java.sql.SQLException;

public interface BaseDao {
	void cleanUp() throws SQLException;
}
