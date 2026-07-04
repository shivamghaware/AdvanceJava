package com.catalog.dao;

import java.sql.SQLException;
import java.util.List;
import com.catalog.entities.Category;

public interface CategoryDao extends BaseDao {
	List<Category> getAllCategories() throws SQLException;
}
