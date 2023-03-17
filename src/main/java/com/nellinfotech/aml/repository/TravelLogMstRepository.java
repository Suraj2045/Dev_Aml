package com.nellinfotech.aml.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

import com.nellinfotech.aml.entities.BranchMst;
import com.nellinfotech.aml.entities.TravelLogMst;

public interface TravelLogMstRepository extends JpaRepository<TravelLogMst, Long> {

	List<TravelLogMst> getTravelLogByBankCode(String bankCode);
	
	@Query(value = " SELECT * FROM travel_log_master t WHERE t.travel_location =:travelLocation and (t.travel_date_from >= :fromDate and t.travel_date_to <= :toDate)", nativeQuery = true)
	List<TravelLogMst> getTravelLogByTravelLocation(String travelLocation,LocalDate fromDate,LocalDate toDate);
	

}
