package com.greatlearning.security.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.security.entity.User;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.service.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		User userResponse = userRepository.save(user);
		return userResponse;
	}
	
	@Override
	public String createUser(String email, String password) {
		User user = new User(email, "Customer", password);
		user = saveUser(user);
		return user.toString();
	}

}
