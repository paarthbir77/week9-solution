package com.greatlearning.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.repository.FinancialReportRepository;
import com.greatlearning.security.serviceImpl.AdminServiceImpl;

import org.junit.Assert;

@SpringBootTest
public class DisplayBillTest {
	
	@Mock
	FinancialReportRepository financialReportRepository;
	@InjectMocks
	AdminServiceImpl adminServiceImpl;
	
	@Test
	@DisplayName(value = "Test display monthly total")
	void shouldShowMonthlyTotal() {
		String month, year;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		year = dateString.substring(0, 4);
		month = dateString.substring(5, 7);
		
		List<Integer> bills= new ArrayList<Integer>();
		bills.add(10);
		bills.add(20);
		bills.add(30);
		Mockito.when(financialReportRepository.findBillsThisMonth(month, year)).thenReturn(bills);
		int result = adminServiceImpl.viewMonthlyTotal();
		Assert.assertEquals(result, 60);
	}
	
	@Test
	@DisplayName(value = "Test display monthly total - negative case")
	void shouldNotShowMonthlyTotal() {
		String month, year;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		year = dateString.substring(0, 4);
		month = dateString.substring(5, 7);
		
		List<Integer> bills= new ArrayList<Integer>();
		
		Mockito.when(financialReportRepository.findBillsThisMonth(month, year)).thenReturn(bills);
		int result = adminServiceImpl.viewMonthlyTotal();
		Assert.assertEquals(result, 0);
	}
	
	@Test
	@DisplayName(value = "Test display todays bills")
	void shouldShowTodayBill() {
String date, month, year;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		year = dateString.substring(0, 4);
		month = dateString.substring(5, 7);
		date = dateString.substring(8,10);
		
		List<FinancialReport> reports = new ArrayList<FinancialReport>();
		reports.add(new FinancialReport("1", 60, date, month, year));
		reports.add(new FinancialReport("3", 70, date, month, year));
		reports.add(new FinancialReport("2", 20, date, month, year));
		reports.add(new FinancialReport("4", 50, date, month, year));
		
		Mockito.when(financialReportRepository.findReportByDate(date, month, year)).thenReturn(reports);
		String result="";
		for(int i=0;i<reports.size();i++) {
			result+=reports.get(i).toString()+"\n";
		}
		String response = adminServiceImpl.viewBillsToday();
		//System.out.println(response);
		Assert.assertEquals(result, response);
	}
	
	@Test
	@DisplayName(value = "Test display todays bills - empty list")
	void shouldShowTodayBillforEmptyList() {
String date, month, year;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		year = dateString.substring(0, 4);
		month = dateString.substring(5, 7);
		date = dateString.substring(8,10);
		
		List<FinancialReport> reports = new ArrayList<FinancialReport>();
		
		Mockito.when(financialReportRepository.findReportByDate(date, month, year)).thenReturn(reports);
		String result="";
		for(int i=0;i<reports.size();i++) {
			result+=reports.get(i).toString()+"\n";
		}
		String response = adminServiceImpl.viewBillsToday();
		Assert.assertEquals(result, response);
	}

}
