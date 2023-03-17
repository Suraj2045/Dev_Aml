package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.CustomerFail;

public interface CustomerFailRepository extends JpaRepository<CustomerFail, Long> {
    
}
