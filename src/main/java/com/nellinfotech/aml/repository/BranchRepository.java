package com.nellinfotech.aml.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.BranchParam;


/**
 * @author Tushar
 *
 */
public interface BranchRepository extends JpaRepository<BranchParam, Long> {
	
}
