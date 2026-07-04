package com.healthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.User;



public interface UserRepository extends JpaRepository<User,Long> {
//derived query
	Optional<User>  findByEmailAndPassword(String em, String pass);
	//derived finder
	Optional<User>  findByEmail(String email);
}
