package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.StateMst;

public interface StateRepository extends JpaRepository<StateMst, Long> {
    
    List<StateMst> findByBankCode(String bankCode);

	List<StateMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);
    
}
