package com.greatlearning.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatlearning.security.entity.InventoryDetails;

@Repository
public interface InventoryDetailsRepository extends JpaRepository<InventoryDetails, Integer> {
	
	@Query("SELECT r.price FROM InventoryDetails r where r.id = :id")
	int findPriceById(@Param("id") int id);
	
	@Query("SELECT r.id, r.itemName, r.price FROM InventoryDetails r")
	List<InventoryDetails> findAllItems();

}
