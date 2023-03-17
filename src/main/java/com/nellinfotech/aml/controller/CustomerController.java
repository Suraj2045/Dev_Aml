package com.nellinfotech.aml.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.dto.RiskProfile;
import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.CustomerService;
import com.nellinfotech.aml.service.HolidayService;

/**
 * @author Paresh K
 */
@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
        /**
     * @author Paresh K
     * @param 
     */
    @PostMapping("/getCustomer")
    public Customer getCustomer(@RequestBody Customer customer) {
        try {
            
            return customerService.getCustomer(customer.getCustCode());
        } catch (Exception e) {
            logger.error("Error in customer controller" + e);
        }
        return null;
    }
    
    @PostMapping("/getAllCustomer")
    public List<Customer> getAllCustomer(@RequestBody Customer customer) {
        try {
            
            return customerService.getAllCustomer(customer);
        } catch (Exception e) {
            logger.error("Error in customer controller" + e);
        }
        return null;
    }
    
 /**
 * @author Paresh K
 * @param 
 */
@PostMapping("/findByCustomerParam")
public List<Customer> findByCustomerParam(@RequestHeader HttpHeaders httpHeaders, @RequestBody Customer customer) {
    try {
    	Header header = HeaderValidator.headerConversion(httpHeaders);
		String bankCode=header.getBankCode();
        return customerService.findByCustomerParam(customer.getCustomerType(), customer.getCustCode(), customer.getCustomerName(),
        		customer.getBaseBrCode(), customer.getCountryOfReg(), customer.getCustomerCategory(),
        		customer.getNationalId1(), customer.getAddr1(), customer.getCity(), customer.getPostalCode(),
        		customer.getMobileNo(), customer.getRiskManual(), bankCode);
      
    } catch (Exception e) {
        logger.error("Error in customer controller" + e);
    }
    return null;
}


/**
* @author Paresh K
* @param 
*/
@PostMapping("/getCustomerList")
public List<Customer> getCustomerList(@RequestBody Customer customer) {
   try {
       
       return customerService.getCustomerList(customer.getBankCode());
     
   } catch (Exception e) {
       logger.error("Error in customer controller" + e);
   }
   return null;
}


/**
 * @author Paresh K
 * @param 
 */
@PostMapping("/getDeDupData")
public List<Customer> getDeDupData(@RequestParam String custCode, @RequestParam String custFName, @RequestParam String custLName, @RequestParam String addr1, @RequestParam String addr2, 
		@RequestParam String custPAN, @RequestParam String custAdhar, @RequestParam String custMobile, @RequestParam String custDOB) {
    try {
        
        return customerService.getDeDupData(custCode, custFName, custLName, addr1, addr2, custPAN, custAdhar, custMobile, custDOB);
      
    } catch (Exception e) {
        logger.error("Error in customer controller" + e);
    }
    return null;
}

@PostMapping("/runDeDup")
public String runDeDup(@RequestParam List<String> parentCustCode, @RequestParam List<String> dedupCustCode) {
    try {
        
         customerService.runDeDup(parentCustCode, dedupCustCode);
         return "Success";
      
    } catch (Exception e) {
        logger.error("Error in customer controller" + e);
    }
    return null;
}


}
