package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.BankMst;

public interface Bankrepository extends JpaRepository<BankMst, Long>{
	@Modifying
    @Query(" Update BankMst b SET b.bankName=:bankName,b.bankLogo=:bankLogo,b.bankDefLang=:C WHERE id=:id")
	public void updateBank(BankMst bank);
	
	//public BankMst findBybank_name(String bank_name);
	


	

}
