package com.catalog.dao;

import java.sql.SQLException;
import java.util.List;

import com.catalog.entities.Product;

public interface ProductDao extends BaseDao {
	List<Product> getProductsByCategory(long categoryId) throws SQLException;
}
