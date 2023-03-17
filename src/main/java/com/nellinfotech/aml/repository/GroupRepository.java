package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.GroupMst;

public interface GroupRepository extends JpaRepository<GroupMst, Long> {
    
	   List<GroupMst> findByIsActive(Integer isActive);

		List<GroupMst> findAllByIsActiveAndBankCode(Integer isActive, String bankCode);

		List<GroupMst> findByIsActiveAndBankCode(Integer isActive, String bankCode);
	

	

	

	
    
}
