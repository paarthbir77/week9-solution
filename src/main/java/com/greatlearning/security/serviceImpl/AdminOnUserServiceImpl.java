package com.greatlearning.security.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.entity.User;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.service.AdminOnUserService;

@Service
public class AdminOnUserServiceImpl implements AdminOnUserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		User userResponse = userRepository.save(user);
		return userResponse;
	}
	
	@Override
	public String createUser(String email, String type, String password) {
		User user = new User(email, type, password);
		user = saveUser(user);
		return user.toString();
	}

	@Override
	public String viewUsers() {
		List<User> users = userRepository.findAllUsers();
		String result="";
		for(int i=0;i<users.size();i++) {
			result+=users.get(i).toString()+"\n";
		}
		return result;
	}
	
	public List<User> getUsersforLogin(){
		return userRepository.findAllUsersforLogin();
	}

	@Override
	public String updateUser(String email, String type) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserById(email);
		if(user == null) {
			return null;
		}
		user.setType(type);
		userRepository.save(user);
		return user.toString();
	}

	@Override
	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		return;
	}
	
	

}
