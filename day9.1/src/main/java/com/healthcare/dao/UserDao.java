package com.healthcare.dao;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public interface UserDao {
//add a method to register(sign up) new user
	String registerUser(User user);

	User getUserDetails(Long userId);

	List<User> getAllUserDetails();

	List<User> getUsersByRoleAndDate(UserRole role1, LocalDate dob1);
}
