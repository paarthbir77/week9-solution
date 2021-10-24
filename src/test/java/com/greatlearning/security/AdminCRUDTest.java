package com.greatlearning.security;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.greatlearning.security.entity.User;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.serviceImpl.AdminOnUserServiceImpl;

@SpringBootTest
public class AdminCRUDTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	AdminOnUserServiceImpl adminService;
	
	@Test
	@DisplayName(value = "Check user creation - type customer")
	void shouldCreateUser() {
		User user = new User("paarthbir", "Customer","password");
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = adminService.createUser("paarthbir","Customer","password");
		Assert.assertEquals(user.toString(), result);
	}
	@Test
	@DisplayName(value = "Check user creation - type admin")
	void shouldCreateUserAdmin() {
		User user = new User("paarthbir", "Admin","password");
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = adminService.createUser("paarthbir","Admin","password");
		Assert.assertEquals(user.toString(), result);
	}
	
	@Test
	@DisplayName(value = "Check user updation - type customer")
	void shouldUpdateUserCustomer() {
		User user = new User("paarthbir", "Customer","password");
		Mockito.when(userRepository.findUserById("paarthbir")).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = adminService.updateUser("paarthbir","Customer");
		Assert.assertEquals(user.toString(), result);
	}
	
	@Test
	@DisplayName(value = "Check user updation - type admin")
	void shouldUpdateUserAdmin() {
		User user = new User("paarthbir", "Admin","password");
		
		Mockito.when(userRepository.findUserById("paarthbir")).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = adminService.updateUser("paarthbir","Admin");
		Mockito.verify(userRepository).findUserById("paarthbir");
		Assert.assertEquals(user.toString(), result);
	}
	
	@Test
	@DisplayName(value = "Check user updation - type Customer negative case")
	void shouldNotUpdateUserCustomer() {
		User user = new User("paarthbir", "Customer","password");
		
		Mockito.when(userRepository.findUserById("paarthbir")).thenReturn(null);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = adminService.updateUser("paarthbir","Customer");
		Mockito.verify(userRepository).findUserById("paarthbir");
		Assert.assertEquals(null, result);
	}
	
	@Test
	@DisplayName(value = "Check user updation - type admin negative case")
	void shouldNotUpdateUserAdmin() {
		User user = new User("paarthbir", "Admin","password");
		
		Mockito.when(userRepository.findUserById("paarthbir")).thenReturn(null);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String result = adminService.updateUser("paarthbir","Admin");
		Mockito.verify(userRepository).findUserById("paarthbir");
		Assert.assertEquals(null, result);
	}
	
	@Test
	@DisplayName(value = "Check user read")
	void shouldShowUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User("paarthbir", "Admin", "password"));
		users.add(new User("abc", "Customer", "12345678"));
		
		Mockito.when(userRepository.findAllUsers()).thenReturn(users);
		String result="";
		for(int i=0;i<users.size();i++) {
			result+=users.get(i).toString()+"\n";
		}
		String response = adminService.viewUsers();
		Assert.assertEquals(result, response);
	}
	
	
}
