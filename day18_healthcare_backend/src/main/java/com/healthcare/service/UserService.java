package com.healthcare.service;

import com.healthcare.dtos.AuthRequest;
import com.healthcare.dtos.AuthResponse;

public interface UserService {

	AuthResponse authenticateUser(AuthRequest request);

}
