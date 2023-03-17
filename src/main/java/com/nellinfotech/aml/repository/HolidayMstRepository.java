package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.HolidayMst;

public interface HolidayMstRepository extends JpaRepository<HolidayMst, Long> {

	HolidayMst findByBranchCode(String branchCode);

	

	

}
