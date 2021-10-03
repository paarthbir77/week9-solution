package com.greatlearning.security.service;
import com.greatlearning.security.entity.User;

public interface RegisterService {
	String createUser(String email, String password);

}
