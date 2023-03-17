package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.AccountFail;

public interface AccountFailRepository extends JpaRepository<AccountFail, Long> {
    
    List<AccountFail> findByStatus(String status);
    
}
