package com.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.entity.UserDtls;
import com.usermanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Override
	public UserDtls createUser(UserDtls user) {
		String hashPassword = PasswordEncoder().encode(user.getPassword());
		user.setPassword(hashPassword);
		return userRepo.save(user);
	}
	private PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}
	
	public boolean authenticateUser(String username, String password) {
	    UserDtls user = userRepo.findByEmail(username);
	    if (user == null) {
	        return false;
	    }
	    String hashedPassword = user.getPassword(); 
	    return BCrypt.checkpw(password, hashedPassword); 
	}

	public UserDtls fetchOneUser(String username) {
		return userRepo.findByEmail(username);
	}

}
