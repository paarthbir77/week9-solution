package com.greatlearning.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.greatlearning.security.config.SecurityConfigurationWithJDBC;
import com.greatlearning.security.entity.MyUserDetails;
import com.greatlearning.security.entity.User;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.service.MyUserDetailsService;
import com.greatlearning.security.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
//import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.Optional;

import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@SpringBootTest
//@RunWith(JUnitPlatform.class)
public class LoginTests {
	
	@InjectMocks
	private MyUserDetailsService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Test
	@DisplayName(value = "Login feature for customer")
	void shouldLogin() {
		
		Optional<User> user  = Optional.of(new User("paarthbir", "Customer","password"));
		Mockito.when(userRepository.findUserByIdLogin("paarthbir")).thenReturn(user);
		UserDetails userDetails = userService.loadUserByUsername("paarthbir");
		
		Assert.assertEquals(user.map(MyUserDetails::new).get().getUsername(), userDetails.getUsername());
		Assert.assertEquals(user.map(MyUserDetails::new).get().getPassword(), userDetails.getPassword());
	}
	
	@Test
	@DisplayName(value = "Login feature for admin")
	void shouldLoginAdmin() {
		
		Optional<User> user  = Optional.of(new User("paarthbir", "Admin","password"));
		Mockito.when(userRepository.findUserByIdLogin("paarthbir")).thenReturn(user);
		UserDetails userDetails = userService.loadUserByUsername("paarthbir");
		
		Assert.assertEquals(user.map(MyUserDetails::new).get().getUsername(), userDetails.getUsername());
		Assert.assertEquals(user.map(MyUserDetails::new).get().getPassword(), userDetails.getPassword());
	}
	
	@Test
	@DisplayName(value = "Login feature for customer - negative")
	void shouldNotLoginCustomer() {
		
		Optional<User> user  = Optional.of(new User("paarthbir", "Customer","password"));
		Mockito.when(userRepository.findUserByIdLogin("paarthbir")).thenReturn(null);
		UserDetails userDetails = userService.loadUserByUsername("paarthbir");
		
		Assert.assertEquals(null, userDetails);
		//Assert.assertEquals(null, userDetails.getPassword());
	}
	@Test
	@DisplayName(value = "Login feature for Admin - negative")
	void shouldNotLoginAdmin() {
		
		Optional<User> user  = Optional.of(new User("paarthbir", "Admin","password"));
		Mockito.when(userRepository.findUserByIdLogin("paarthbir")).thenReturn(null);
		UserDetails userDetails = userService.loadUserByUsername("paarthbir");
		
		Assert.assertEquals(null, userDetails);
		//Assert.assertEquals(null, userDetails.getPassword());
	}
	

}
