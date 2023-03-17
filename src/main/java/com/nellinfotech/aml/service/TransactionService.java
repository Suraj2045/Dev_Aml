package com.nellinfotech.aml.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.model.Header;

public interface TransactionService {

	
	//List<Transaction> findByTransactionParam(String acctNo, String custCode, String cashflowType, String txnAmount, java.sql.Date toDate, java.sql.Date fromDate);
			//,			Date txnDtTm);

	Transaction getTransactionDetails(String txnNumber, String custCode);

	List<Transaction> findByTransactionParam(String acctNo, String custCode, String cashflowType, BigDecimal txnAmount,
			String toDate, String fromDate, String operator);

	List<Transaction> getTransactionByInterval(String acctNo, String custCode, String interval);

	
	       
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

	
    
    
    
}
