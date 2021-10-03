package com.greatlearning.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatlearning.security.entity.FinancialReport;
import com.greatlearning.security.entity.InventoryDetails;

@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReport,Integer>{
	
	@Query("SELECT new FinancialReport(r.id,r.userID,r.billTotal, r.date, r.month, r.year) FROM FinancialReport r where r.date = :date AND r.month = :month AND r.year = :year")
	List<FinancialReport> findReportByDate(@Param("date") String date, @Param("month") String month, @Param("year") String year);
	
	@Query("SELECT r.id,r.userID,r.billTotal, r.date, r.month, r.year FROM FinancialReport r")
	List<FinancialReport> findAllReports();
	
	@Query("SELECT r.billTotal FROM FinancialReport r where r.month = :month AND r.year = :year")
	List<Integer> findBillsThisMonth(@Param("month") String month, @Param("year") String year);

}
