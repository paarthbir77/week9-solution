package com.greatlearning.security.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class FinancialReport {
	
	public FinancialReport() {
		
	}
	
	public FinancialReport(int id,String userId, int billTotal, String date, String month, String year){
		this.id = id;
		this.userID = userId;
		this.billTotal = billTotal;
		this.date = date;
		this.month = month;
		this.year = year;
	}
	
	public FinancialReport(String userId, int billTotal, String date, String month, String year){
		this.userID = userId;
		this.billTotal = billTotal;
		this.date = date;
		this.month = month;
		this.year = year;
	}
	public String getUserId() {
		return this.userID;
	}
	public int getBill() {
		return this.billTotal;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID",unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "USERID")
	private String userID;
	
	@Column(name = "BillTotal")
	private int billTotal;
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "Month")
	private String month;
	
	@Column(name = "YEAR")
	private String year;
	
	@Override
	public String toString() {
		return "ITEM: id=" + id + ", userID=" + userID + ", billTotal=" + billTotal + ", Date="+date+"-"+month+"-"+year;
	}
}
