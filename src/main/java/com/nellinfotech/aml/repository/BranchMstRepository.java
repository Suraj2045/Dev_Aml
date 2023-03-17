package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.BranchMst;

public interface BranchMstRepository extends JpaRepository<BranchMst, Long> {
    BranchMst findBybranchCode(String branchCode);
    
    
    List<BranchMst> findBybankCode(String bankCode);
    
}
