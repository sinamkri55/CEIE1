package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

@Service
public class UserService {
     @Autowired
	private UserRepository userRepo;
     
     
     
     
     public boolean verify(String verificationcode) {
	User user=userRepo.findByVerificationCode(verificationcode);
	
	if(user==null || user.isEnabled()) {
		return false;
	}
	else
	{
		user.setVerificationcode(null);
		user.setEnabled(true);
		userRepo.save(user);
		return true;
	}
     }
}
