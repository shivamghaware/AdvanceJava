package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
