package com.greatlearning.security.service;

import java.util.List;

import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.FinancialReport;

public interface UserService {

	boolean checkInventoryExist(int id);

	String viewInventory();

	FinancialReport placeOrder(int orderId[]);

}