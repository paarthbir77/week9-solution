package com.greatlearning.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetails {

	InventoryDetails(){	
	
	}
	public InventoryDetails(int id, String itemName, int price){	
		this.id = id;
		this.itemName = itemName;
		this.price = price;
	}
	public InventoryDetails(String itemName, int price){	
		this.itemName = itemName;
		this.price = price;
	}
	
	@Id
	private Integer id;
	
	@Column(name = "ITEM_NAME")
	private String itemName;
	
	@Column(name = "PRICE")
	private int price;
	
	@Override
	public String toString() {
		return "ITEM: id=" + id + ", itemName=" + itemName + ", price=" + price;
	}
}
