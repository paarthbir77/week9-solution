package com.greatlearning.security.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.service.AdminService;

@RestController
//@RequestMapping("/organicVeggies")
public class AdminController {
	
	@Autowired
	AdminService adminService;

	@GetMapping("/viewMonthlyFinancials")
	public int viewMonthlyFinancials() {
		return adminService.viewMonthlyTotal();
	}
	@GetMapping("/viewBillsToday")
	public String viewBillsToday() {
		return adminService.viewBillsToday();
	}

}
