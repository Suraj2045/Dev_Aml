package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.RoleMenuMap;

/**
 * @author ASHWIN
 */
public interface RoleMenuMapRepository extends JpaRepository<RoleMenuMap, Long> {
    
    public RoleMenuMap findByRoleCode(String roleCode);
    
}