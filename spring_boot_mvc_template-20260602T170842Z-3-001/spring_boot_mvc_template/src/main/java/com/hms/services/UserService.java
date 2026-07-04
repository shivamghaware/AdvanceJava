package com.hms.services;

import com.hms.entities.UserDetails;

public interface UserService {
	UserDetails authenticateUser(String email,String password);
}
