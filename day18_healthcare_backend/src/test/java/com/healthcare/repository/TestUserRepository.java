package com.healthcare.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.healthcare.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestUserRepository {
	@Autowired
	private UserRepository userRepo;

	@Test
	void testUserSignIn() {
		Optional<User> optional = userRepo.findByEmailAndPassword("abc@gmail.com", "12345");
		assertEquals(true, optional.isEmpty());
	}

}
