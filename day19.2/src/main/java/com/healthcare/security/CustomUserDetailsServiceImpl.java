package com.healthcare.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.entities.User;
import com.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User by email deosn't exist!!!!!!!!!!!"));
		//user by email - exists
		return new CustomUserDetailsImpl(user);
	}

}
