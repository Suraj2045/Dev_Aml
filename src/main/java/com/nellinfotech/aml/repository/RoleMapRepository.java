package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.MenuDtls;

public interface RoleMapRepository extends JpaRepository<MenuDtls, Long> {
    
    List<MenuDtls> findByMenuCode(String menuCode);
    
}
