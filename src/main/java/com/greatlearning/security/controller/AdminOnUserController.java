package com.greatlearning.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.User;
import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.service.AdminOnUserService;

@RestController
public class AdminOnUserController {
	@Autowired
	AdminOnUserService adminService;
	
	@GetMapping("/createUser")
	public String createUser(String email, String type, String password) {
		return adminService.createUser(email, type, password);
	}
	@GetMapping("/viewUsers")
	public String viewUsers() {
		return adminService.viewUsers();
	}
	@GetMapping("/updateUsers")
	public String updateUsers(String email, String type) {
		return adminService.updateUser(email, type);
	}
	@GetMapping("/deleteUsers")
	public void deleteUser(String email) {
		adminService.deleteUser(email);
		return; 
	}
}


