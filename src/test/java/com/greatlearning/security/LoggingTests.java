package com.greatlearning.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.logging.LoggingClass;
import com.greatlearning.security.repository.FinancialReportRepository;
import com.greatlearning.security.repository.InventoryDetailsRepository;
import com.greatlearning.security.serviceImpl.UserServiceImpl;

@SpringBootTest
public class LoggingTests {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	InventoryDetailsRepository repo;
	@Mock
	FinancialReportRepository financialReportRepository;
	@Mock
	LoggingClass logger;
	
	@Test
	@DisplayName(value = "Check log generation while placing order")
	void shouldCallLoggerinUserService() {
		//return for check inventory
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
		
		System.out.println(finReport.toString());
		FinancialReport result = userServiceImpl.placeOrder(new int[]{1, 2, 3});
		
		Mockito.verify(logger).myPointcut();
		
		System.out.println(result.toString());
		Assert.assertEquals(finReport.getBill(), result.getBill());
	}

}
