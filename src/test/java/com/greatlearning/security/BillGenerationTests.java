package com.greatlearning.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.repository.FinancialReportRepository;
import com.greatlearning.security.repository.InventoryDetailsRepository;
import com.greatlearning.security.serviceImpl.UserServiceImpl;

@SpringBootTest
public class BillGenerationTests {
	
	@Mock
	InventoryDetailsRepository repo;
	@Mock
	FinancialReportRepository financialReportRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Test
	@DisplayName(value = "Check bill generation while placing order")
	void shouldSelectFromMenu() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		FinancialReport finReport = new FinancialReport("", 60, dateString.substring(8,10), dateString.substring(5, 7), dateString.substring(0, 4));
		//return for check inventory
		Mockito.when(repo.existsById(1)).thenReturn(true);
		Mockito.when(repo.existsById(2)).thenReturn(true);
		Mockito.when(repo.existsById(3)).thenReturn(true);
		// Return prices for orders
		Mockito.when(repo.findPriceById(1)).thenReturn(10);
		Mockito.when(repo.findPriceById(2)).thenReturn(20);
		Mockito.when(repo.findPriceById(3)).thenReturn(30);
		// To mock save
		Mockito.when(financialReportRepository.save(Mockito.any(FinancialReport.class))).thenReturn(finReport);
		
		FinancialReport result = userServiceImpl.placeOrder(new int[]{1, 2, 3});
		Assert.assertEquals(finReport.getBill(), result.getBill());
		Assert.assertEquals(finReport.getUserId(), result.getUserId());
		Assert.assertEquals(finReport, result);
	}
	
	@Test
	@DisplayName(value = "Check bill generation while placing order with illegal entry")
	void shouldPlaceOrderWithIllegalEntry() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		FinancialReport finReport = new FinancialReport("", 40, dateString.substring(8,10), dateString.substring(5, 7), dateString.substring(0, 4));
		//return for check inventory
		Mockito.when(repo.existsById(1)).thenReturn(true);
		Mockito.when(repo.existsById(2)).thenReturn(false);
		Mockito.when(repo.existsById(3)).thenReturn(true);
		// Return prices for orders
		Mockito.when(repo.findPriceById(1)).thenReturn(10);
		Mockito.when(repo.findPriceById(2)).thenReturn(20);
		Mockito.when(repo.findPriceById(3)).thenReturn(30);
		// To mock save
		Mockito.when(financialReportRepository.save(Mockito.any(FinancialReport.class))).thenReturn(finReport);
		
		FinancialReport result = userServiceImpl.placeOrder(new int[]{1, 2, 3});
		Assert.assertEquals(finReport.getBill(), result.getBill());
		Assert.assertEquals(finReport.getUserId(), result.getUserId());
		Assert.assertEquals(finReport, result);
	}
	
	@Test
	@DisplayName(value = "Check bill generation while placing order with 0 entry")
	void shouldNotPlaceOrderWithIllegalEntry() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateString= now.toString();
		FinancialReport finReport = new FinancialReport("", 0, dateString.substring(8,10), dateString.substring(5, 7), dateString.substring(0, 4));
		//return for check inventory
		Mockito.when(repo.existsById(2)).thenReturn(false);
		// Return prices for orders
		Mockito.when(repo.findPriceById(2)).thenReturn(20);
		// To mock save
		Mockito.when(financialReportRepository.save(Mockito.any(FinancialReport.class))).thenReturn(finReport);
		
		FinancialReport result = userServiceImpl.placeOrder(new int[]{2});
		Assert.assertEquals(finReport.getBill(), result.getBill());
		Assert.assertEquals(finReport.getUserId(), result.getUserId());
		Assert.assertEquals(finReport, result);
	}

}
