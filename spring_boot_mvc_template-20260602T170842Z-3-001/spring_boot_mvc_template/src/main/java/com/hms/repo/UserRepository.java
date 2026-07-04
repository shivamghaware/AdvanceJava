package com.hms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

	Optional<UserDetails> findByEmailAndPassword(String email,String password);
}
