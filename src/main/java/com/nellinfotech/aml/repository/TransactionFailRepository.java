package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.TransactionFail;

public interface TransactionFailRepository extends JpaRepository<TransactionFail, Long> {
    
}
