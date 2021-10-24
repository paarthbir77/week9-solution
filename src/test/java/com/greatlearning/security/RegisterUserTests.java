package com.greatlearning.security;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.greatlearning.security.entity.User;
import com.greatlearning.security.repository.InventoryDetailsRepository;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.service.RegisterService;
import com.greatlearning.security.service.UserService;
import com.greatlearning.security.serviceImpl.RegisterServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
//import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@SpringBootTest
public class RegisterUserTests {
	

	@Mock
	InventoryDetailsRepository repo;
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	RegisterServiceImpl registerServiceImpl;
	
	@Test
	@DisplayName(value = "Check if customer abe to register")
	void checkUserCreation() {
		User user = new User("paarthbir", "Customer","password");
		//Mockito when clause for save function
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = registerServiceImpl.createUser("paarthbir", "password");
		Assert.assertEquals(user.toString(), result);
	}


}
