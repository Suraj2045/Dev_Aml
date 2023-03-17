package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	List<Account> findByCustCode(String custCode);

	List<Account> findByBankCode(String bankCode);
	
	
	
    
}
