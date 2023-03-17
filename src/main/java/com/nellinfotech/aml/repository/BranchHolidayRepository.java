package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.BranchHolidayMap;

public interface BranchHolidayRepository extends JpaRepository<BranchHolidayMap, Long> {
    
    @Query(value = " SELECT * FROM branch_holiday_map WHERE BRANCH_CODE=?1 AND IS_ACTIVE = ?2", nativeQuery = true)
    List<BranchHolidayMap> findBybrCode(String brCode, Integer isactive);
    
    BranchHolidayMap findAllByIsActiveAndId(Integer isActive, Long id);
    
}
