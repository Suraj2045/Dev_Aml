package com.nellinfotech.aml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.Account;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.AccountService;


/**
 * @author Paresh K
 */
@CrossOrigin(origins = "*")
@RestController
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    Logger logger = LoggerFactory.getLogger(AccountController.class);
        /**
     * @author Paresh K
     * @param 
     */
    @PostMapping("/getAccountNumber")
    public List<Account> getCustomer(@RequestHeader HttpHeaders httpHeaders, @RequestBody Account account) {
        try {
            
            return accountService.getAccountNumber(account.getCustCode());
        } catch (Exception e) {
            logger.error("Error in AccountController" + e);
        }
        return null;
    }
    
    @GetMapping("/getAccountNumberByBank")
    public List<Account> getAccountNumberByBank(@RequestHeader HttpHeaders httpHeaders) {
        try {
        	Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
            return accountService.getAccountNumberByBank(bankCode);
        } catch (Exception e) {
            logger.error("Error in AccountController" + e);
        }
        return null;
    }
    
    /**
 * @author Paresh K
 * @param 
 */
//@PostMapping("/findByCustomerParam")
//public List<Customer> findByCustomerParam(@RequestBody Customer customer) {
//    try {
//        
//        return customerService.findByCustomerParam(customer.getCustomerType(), customer.getCustCode(), customer.getCustomerName(),
//        		customer.getBaseBrCode(), customer.getCountryOfReg(), customer.getCustomerCategory(),
//        		customer.getNationalId1(), customer.getAddr1(), customer.getCity(), customer.getPostalCode(),
//        		customer.getMobileNo(), customer.getRiskManual());
//      
//    } catch (Exception e) {
//        logger.error("Error in customer controller" + e);
//    }
//    return null;
//}
//
//
//
//@PostMapping("/getTransactionDetails")
//public List<Customer> getTransactionDetails(@RequestBody Customer customer) {
//    try {
//        
//        return customerService.getTransactionDetails(customer.getCustCode());
//      
//    } catch (Exception e) {
//        logger.error("Error in customer controller" + e);
//    }
//    return null;
//}
//
//@PostMapping("/getCustomer")
//public Customer getAccountDetails(@RequestBody Customer customer) {
//    try {
//        
//        return customerService.getAccountDetails(customer.getCustCode());
//    } catch (Exception e) {
//        logger.error("Error in customer controller" + e);
//    }
//    return null;
//}
//
//@PostMapping("/getRiskProfile")
//public Customer getRiskProfile(@RequestBody Customer customer) {
//    try {
//        
//        return customerService.getRiskProfile(customer.getCustCode());
//    } catch (Exception e) {
//        logger.error("Error in customer controller" + e);
//    }
//    return null;
//}
}
