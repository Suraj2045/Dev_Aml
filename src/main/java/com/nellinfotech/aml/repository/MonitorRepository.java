package com.nellinfotech.aml.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.nellinfotech.aml.entities.Monitor;

public interface MonitorRepository  extends JpaRepository<Monitor, Long> {
	
	@Query(value = "SELECT * FROM monitor m WHERE m.cpp_date BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<Monitor> getAllBetweenDates(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
	                                       @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate);

	List<Monitor> getMonitoringByBankCode(String bankCode);
	
}
