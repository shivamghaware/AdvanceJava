package com.hms.service;

import com.hms.dtos.AuthRequestDTO;
import com.hms.dtos.AuthResponseDTO;

public interface UserService {

	AuthResponseDTO authenticateUserByEmailPass(AuthRequestDTO authrequest);

}
