package com.greatlearning.security.service;

import java.util.List;

import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.FinancialReport;

public interface AdminService {
	int viewMonthlyTotal();
	String viewBillsToday();

}
