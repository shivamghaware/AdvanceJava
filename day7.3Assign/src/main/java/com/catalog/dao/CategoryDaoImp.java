package com.catalog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.catalog.dbutils.DbUtils;
import com.catalog.entities.Category;

public class CategoryDaoImp implements CategoryDao {
	private Connection connection;
	private PreparedStatement pst1;

	public CategoryDaoImp(String dbURL,String userName,String password) throws SQLException {
		connection = DbUtils.getConnection(dbURL, userName, password);
		pst1 = connection.prepareStatement("select * from categories");
	}

	@Override
	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}

		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public List<Category> getAllCategories() throws SQLException {
		List<Category> categories = new ArrayList<>();

		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				categories.add(new Category(rst.getLong(1), rst.getString(2), rst.getString(3)));
			}
		}
		return categories;
	}

}
