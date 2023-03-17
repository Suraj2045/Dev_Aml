package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.Account;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.model.Header;

public interface AccountService {
    
        
//    Customer getCustomer(String custCode);
//
//	public List<Customer> findByCustomerParam(String customerType, String custCode, String customerName, String baseBrCode,
//			String countryOfReg, String customerCategory, String nationalId1, String addr1, String city,
//			String postalCode, String mobileNo, Double riskManual);
//
//	List<Customer> getTransactionDetails(String custCode);
//
//	Customer getAccountDetails(String custCode);
//
//	Customer getRiskProfile(String custCode);

	List<Account> getAccountNumber(String custCode);

	List<Account> getAccountNumberByBank(String bankCode);

	
    
    
    
}
