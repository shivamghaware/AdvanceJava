package com.catalog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.catalog.dbutils.DbUtils;
import com.catalog.entities.Product;

public class ProductDaoImp implements ProductDao {
	private Connection connection;
	private PreparedStatement pst1;

	public ProductDaoImp(String dbURL,String userName,String password) throws SQLException {
		connection = DbUtils.getConnection(dbURL,userName,password);
		pst1 = connection.prepareStatement("select * from products where category_id=?");
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
	public List<Product> getProductsByCategory(long categoryId) throws SQLException {
		pst1.setLong(1, categoryId);
		List<Product> products = new ArrayList<>();

		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				products.add(
						new Product(rst.getLong(1), rst.getString(2), rst.getDouble(3), rst.getInt(4), rst.getLong(5)));
			}
		}
		return products;
	}

}
