package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.MenuMst;

/**
 * @author ASHWIN
 */
public interface MenuMstRepository extends JpaRepository<MenuMst, Long> {
    
    public MenuMst findByMenuCode(String menuCode);
    
}