package com.hms.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hms.dtos.AuthRequestDTO;
import com.hms.dtos.AuthResponseDTO;
import com.hms.entities.User;
import com.hms.exceptions.AuthenticationException;
import com.hms.repo.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
	private final UserRepository userRepo;


	@Override
	public AuthResponseDTO authenticateUserByEmailPass(AuthRequestDTO authrequest) {
		User user=userRepo.findByEmailAndPassword(authrequest.email(),authrequest.password()).orElseThrow(()->new AuthenticationException("Invalid Email Or Password"));
		
		AuthResponseDTO resp= modelMapper.map(user, AuthResponseDTO.class);
		resp.setMessage("Successfull");
		return resp;
	}

}
