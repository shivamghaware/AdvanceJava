package com.healthcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.AuthenticationException;
import com.healthcare.dtos.AuthRequest;
import com.healthcare.dtos.AuthResponse;
import com.healthcare.entities.User;
import com.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	//depcy 
	private final UserRepository userRepo;
	private final ModelMapper mapper;

	@Override
	public AuthResponse authenticateUser(AuthRequest request) {
		//invoke dao's method
		User user=userRepo.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(() -> new AuthenticationException("Invalid email or password !!!!!"));
		//=> valid login 
		//Instead simply call map method of ModelMapper
		AuthResponse response = mapper.map(user, AuthResponse.class);
		response.setMessage("Login Successful!");
		return response;
	}

}
