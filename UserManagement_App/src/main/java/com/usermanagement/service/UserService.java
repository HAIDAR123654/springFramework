package com.usermanagement.service;

import org.springframework.stereotype.Service;

import com.usermanagement.entity.UserDtls;

@Service
public interface UserService {

	public UserDtls createUser(UserDtls user);
	
	public boolean checkEmail(String email);
	
	public boolean authenticateUser(String username, String password);
	
	public UserDtls fetchOneUser(String username);
}
