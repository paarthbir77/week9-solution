package com.greatlearning.security.serviceImpl;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.repository.InventoryDetailsRepository;
import com.greatlearning.security.repository.FinancialReportRepository;
import com.greatlearning.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository employeeRepository;

	@Autowired
	InventoryDetailsRepository inventoryDetailsRepository;

	@Autowired
	FinancialReportRepository financialReportRepository;
	
	@Override
	public boolean checkInventoryExist(int id) {
		return inventoryDetailsRepository.existsById(id);
	}
	
	@Override
	public String viewInventory() {
		List<InventoryDetails> items = inventoryDetailsRepository.findAll();
		String result="";
		for(int i=0;i<items.size();i++) {
			result+=items.get(i).toString()+"\n";
		}
		return result;
	}
	

	@Transactional
	public FinancialReport saveFinReport(FinancialReport finReport) {
		FinancialReport finResponse = financialReportRepository.save(finReport);
		return finResponse;
	}
	
	@Override
	public FinancialReport placeOrder(int orderId[]) {
		int bill=0;
		for(int i=0;i<orderId.length;i++) {
			if(checkInventoryExist(orderId[i])) {
				bill += inventoryDetailsRepository.findPriceById(orderId[i]);
			}
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		String username="";
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		FinancialReport finReport = new FinancialReport(username, bill, dateString.substring(8,10), dateString.substring(5, 7), dateString.substring(0, 4));
		finReport = saveFinReport(finReport);
		return finReport;
	}
	
}
