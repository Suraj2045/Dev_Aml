package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.CurrencyMst;

public interface CurrencyRepository extends JpaRepository<CurrencyMst, Long> {
    
    List<CurrencyMst> findByBankCode(String bankCode);

	List<CurrencyMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);
    
}
