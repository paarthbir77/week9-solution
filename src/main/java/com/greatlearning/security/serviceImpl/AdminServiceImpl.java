package com.greatlearning.security.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.repository.UserRepository;
import com.greatlearning.security.repository.InventoryDetailsRepository;
import com.greatlearning.security.repository.FinancialReportRepository;
import com.greatlearning.security.service.AdminService;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.repository.FinancialReportRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	FinancialReportRepository financialReportRepository;
	
	@Override
	public int viewMonthlyTotal() {
		String month, year;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		year = dateString.substring(0, 4);
		month = dateString.substring(5, 7);
		List<Integer> billTotals = financialReportRepository.findBillsThisMonth(month, year);
		int total = 0;
		for(int i = 0; i<billTotals.size(); i++) {
			total += billTotals.get(i);
		}
		return total;
	}

	@Override
	public String viewBillsToday() {
		String date, month, year;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		year = dateString.substring(0, 4);
		month = dateString.substring(5, 7);
		date = dateString.substring(8,10);
		List<FinancialReport> reports = financialReportRepository.findReportByDate(date, month, year);
		//List<FinancialReport> reports = financialReportRepository.findAll();
		String result="";
		for(int i=0;i<reports.size();i++) {
			//System.out.println(reports.get(i));
			result+=reports.get(i).toString()+"\n";
		}
		return result;
		//return null;
	}
	

}
