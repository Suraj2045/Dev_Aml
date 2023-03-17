package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.CityMst;

public interface CityRepository extends JpaRepository<CityMst, Long> {
    
    List<CityMst> findByBankCode(String bankCode);

	List<CityMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);
    
}
