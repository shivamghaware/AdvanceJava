package com.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.entities.UserDetails;
import com.healthcare.repository.UserDetailsRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// depcy
	@Autowired
	private UserDetailsRepository userRepo;

	@Override
	public List<UserDetails> getAllUserDetails() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public String addNewUser(UserDetails user) {
		// save
		UserDetails savedUser = userRepo.save(user);
		return "User details added with ID " + savedUser.getId();
	}

	@Override
	public String deleteUserDetails(Long userId) {
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return "User details deleted....";
		}
		throw new ResourceNotFoundException("User not found - invalid user id !!!!");
	}

	@Override
	public UserDetails getUserDetails(Long uid) {
		// TODO Auto-generated method stub
		return userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("Invalid user id!!!!"));
	}

	@Override
	public String updateUserDetails(Long userId, UserDetails details) {
		UserDetails existingUserDetails=getUserDetails(userId);
		existingUserDetails.setFirstName(details.getFirstName());
		existingUserDetails.setLastName(details.getLastName());
		existingUserDetails.setPassword(details.getPassword());
		existingUserDetails.setRegAmount(details.getRegAmount());
		return "user details updated....";
	}
	

}
