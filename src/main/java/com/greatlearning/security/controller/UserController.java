package com.greatlearning.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/checkInventory")
	public boolean checkInventoryExist(int id) {
		return userService.checkInventoryExist(id);
	}

	@GetMapping("/viewInventory")
	public String viewInventory() {
		return userService.viewInventory();
	}

	@PostMapping("/placeOrder")
	public String placeOrder(int orderId[]) {
		return userService.placeOrder(orderId).toString();
	}

}
