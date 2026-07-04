package com.hms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmailAndPassword(String email,String password);
}
