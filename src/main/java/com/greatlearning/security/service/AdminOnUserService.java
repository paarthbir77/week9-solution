package com.greatlearning.security.service;

import com.greatlearning.security.entity.User;

public interface AdminOnUserService {
	
	String createUser(String email, String type, String password);
	String viewUsers();
	String updateUser(String email, String type);
	void deleteUser(String email);
}
