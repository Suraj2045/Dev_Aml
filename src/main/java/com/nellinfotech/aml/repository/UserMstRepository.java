package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.UserMst;

/**
 * @author ASHWIN
 */
public interface UserMstRepository extends JpaRepository<UserMst, Long> {
    
	 public UserMst findByUserId(String userId);
	    
	    public UserMst findByBankCodeAndUserId(String bankCode, String userId);

		public List<UserMst> findByBankCode(String bankCode);

		public List<UserMst> findByBankCodeAndIsActive(String bankCode, Integer isActive);

		public List<UserMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);
    
}