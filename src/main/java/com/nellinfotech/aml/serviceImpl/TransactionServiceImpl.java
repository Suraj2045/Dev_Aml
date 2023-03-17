package com.nellinfotech.aml.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.TransactionRepository;
import com.nellinfotech.aml.service.CustomerService;
import com.nellinfotech.aml.service.TransactionService;

/**
 * @author TUSHAR
 */

@Service
public class TransactionServiceImpl implements TransactionService {

	
    
    @Autowired
    TransactionRepository transactionRpository;
    
        @Autowired
    DateValidator dateValidator;
        
        @Override
    	public List<Transaction> findByTransactionParam(String acctNo, String custCode, String cashflowType, BigDecimal txnAmount, String toDate, String fromDate, String operator){
    			
        	try {
        		
				return transactionRpository.findByTransactionParam(acctNo, custCode, cashflowType, txnAmount, toDate, fromDate, operator);
		    							
			}
			catch(Exception e) {
				e.printStackTrace();
			}
        	
			return null;
    		
    	}

		@Override
		public Transaction getTransactionDetails(String txnNumber, String custCode) {
			try {
        		
				return transactionRpository.getTransactionDetails(txnNumber, custCode);
		   		}
			catch(Exception e) {
				e.printStackTrace();
			}
        	System.out.println("step3");
			return null;
		}

		@Override
		public List<Transaction> getTransactionByInterval(String acctNo, String custCode, String interval) {
			
			String interval1="1 Week";
			String interval2="1 Month";
			String interval3="3 Month";
			String interval4="6 Month";
			String interval5="1 Year";
			
			if(interval.equalsIgnoreCase(interval1)) {
				try {
	        		
					return transactionRpository.getTransactionBy1WeekInterval(acctNo, custCode);
	        		}
				catch(Exception e) {
					e.printStackTrace();
				}
				}
			else if(interval.equalsIgnoreCase(interval2)) {
			try {
        		return transactionRpository.getTransactionBy1MonthInterval(acctNo, custCode);
        		}
			catch(Exception e) {
				e.printStackTrace();
			}
			}
        		else if(interval.equalsIgnoreCase(interval3)) {
        			try {
        				return transactionRpository.getTransactionBy3MonthInterval(acctNo, custCode);
            		}
		    							
			catch(Exception e) {
				e.printStackTrace();
			}
			}
			
        		else if(interval.equalsIgnoreCase(interval4)) {
        			try {
        				return transactionRpository.getTransactionBy6MonthInterval(acctNo, custCode);
            		}
		    							
			catch(Exception e) {
				e.printStackTrace();
			}
			}
			
        		else if(interval.equalsIgnoreCase(interval5)) {
        			try {
        				return transactionRpository.getTransactionBy1YearInterval(acctNo, custCode);
            		}
		    							
			catch(Exception e) {
				e.printStackTrace();
			}
			}
			
        	

			return null;
		}

        
}
