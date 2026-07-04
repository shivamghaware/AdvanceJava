package com.healthcare.service;

import java.util.List;

import com.healthcare.entities.UserDetails;

public interface UserService {
//add a method to get all user details
	List<UserDetails> getAllUserDetails();
	//add a method to add new user
	String addNewUser(UserDetails user);
	String deleteUserDetails(Long userId);
	UserDetails getUserDetails(Long uid);
	String updateUserDetails(Long userId, UserDetails details);
}
