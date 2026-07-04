package com.hms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entities.UserDetails;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails authenticateUser(String email, String password) {
		
		return userRepository.findByEmailAndPassword(email, password)
				.orElseThrow(()->new ResourceNotFoundException("User Not FOund!!"));
	}

}
