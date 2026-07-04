package com.healthcare.service;

import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AuthRequest;
import com.healthcare.dtos.AuthResp;

public interface UserService {

	AuthResp authenticateUser(AuthRequest request);

	ApiResponse encrytPasswords();

}
