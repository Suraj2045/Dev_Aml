package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.MenuRoleMap;

public interface MenuRoleMapReposritory extends JpaRepository<MenuRoleMap, Long> {
    
    List<MenuRoleMap> findByRoleCode(String roleCode);
    
}
