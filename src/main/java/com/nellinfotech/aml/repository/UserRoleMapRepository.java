package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.UserRoleMap;

/**
 * @author ASHWIN
 */
public interface UserRoleMapRepository extends JpaRepository<UserRoleMap, Long> {
    
    public UserRoleMap findByUserId(String userId);
    
    public UserRoleMap findByUserIdAndRoleCodeAndBrCode(String userId, String roleCode, String brCode);
    
}