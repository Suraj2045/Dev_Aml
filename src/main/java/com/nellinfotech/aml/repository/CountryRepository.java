package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.CountryMst;

public interface CountryRepository extends JpaRepository<CountryMst, Long> {
    
    List<CountryMst> findByBankCode(String bankCode);

	List<CountryMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);
    
}
