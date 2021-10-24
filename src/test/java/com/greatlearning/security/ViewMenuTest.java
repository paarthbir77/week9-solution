package com.greatlearning.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.entity.InventoryDetails;
import com.greatlearning.security.repository.FinancialReportRepository;
import com.greatlearning.security.repository.InventoryDetailsRepository;
import com.greatlearning.security.service.UserService;
import com.greatlearning.security.serviceImpl.UserServiceImpl;

@SpringBootTest
public class ViewMenuTest {
	@Mock
	InventoryDetailsRepository repo;
	@Mock
	FinancialReportRepository financialReportRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	int orderId[] = new int[] {1, 2, 3};
	
	@Test
	@DisplayName(value = "Check if item is in inventory")
	void shouldCheckInventory() {
		Mockito.when(repo.existsById(1)).thenReturn(true);
		boolean result = userServiceImpl.checkInventoryExist(1);
		Assert.assertEquals(result, true);
	}
	@Test
	@DisplayName(value = "Check if item is in inventory negative case")
	void shouldCheckInventoryFalse() {
		Mockito.when(repo.existsById(1)).thenReturn(false);
		boolean result = userServiceImpl.checkInventoryExist(1);
		Assert.assertEquals(result, false);
	}
	
	@Test
	@DisplayName(value="check view all items")
	void shouldShowItems() {
		List<InventoryDetails> items = new ArrayList<InventoryDetails>();
		items.add(new InventoryDetails(1, "pizza", 100));
		items.add(new InventoryDetails(2, "pasta", 80));
		String result="";
		for(int i=0;i<items.size();i++) {
			result+=items.get(i).toString()+"\n";
		}
		Mockito.when(repo.findAll()).thenReturn(items);
		String response = userServiceImpl.viewInventory();
		Assert.assertEquals(result, response);
	}

}
