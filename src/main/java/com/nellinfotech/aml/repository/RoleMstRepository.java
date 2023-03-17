package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.RoleMst;

/**
 * @author ASHWIN
 */
public interface RoleMstRepository extends JpaRepository<RoleMst, Long> {
    
    public RoleMst findByRoleCode(String roleCode);

	public List<RoleMst> findByBankCode(String banbkCode);

	public List<RoleMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);
    
}