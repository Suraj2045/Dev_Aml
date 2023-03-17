package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.UserPasswordHistoryEntity;

public interface UserPasswordHistoryRepository extends JpaRepository<UserPasswordHistoryEntity, Long> {
    
}
