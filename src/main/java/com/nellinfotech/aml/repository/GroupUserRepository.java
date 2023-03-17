package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.GroupUserMap;

public interface GroupUserRepository extends JpaRepository<GroupUserMap, Long> {
    
    List<GroupUserMap> findByUserId(String userId);
    
}
