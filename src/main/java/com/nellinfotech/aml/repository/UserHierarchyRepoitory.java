package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nellinfotech.aml.entities.UserHierarchy;

public interface UserHierarchyRepoitory extends JpaRepository<UserHierarchy, Long> {
    
List<UserHierarchy> findByUserId(String userId);
    
    UserHierarchy findAllByUserIdAndReportedUserId(String userId, String reportedUserId);

	List<UserHierarchy> findByUserIdAndBankCodeAndIsActive(String userId, String bankCode, Integer isActive);
	@Transactional
    @Modifying
	void deleteByIdAndBankCode(Long id, String bankCode);
    
}
