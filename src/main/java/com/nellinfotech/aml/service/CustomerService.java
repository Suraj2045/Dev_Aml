package com.nellinfotech.aml.service;

import java.util.ArrayList;
import java.util.List;

import com.nellinfotech.aml.dto.RiskProfile;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.model.Header;

public interface CustomerService {
    
        
    Customer getCustomer(String custCode);

	public List<Customer> findByCustomerParam(String customerType, String custCode, String customerName, String baseBrCode,
			String countryOfReg, String customerCategory, String nationalId1, String addr1, String city,
			String postalCode, String mobileNo, Double double1, String bankCode);

	Customer getCustomerMatch(String custCode, String customerFName);

	List<Customer> getDeDupData(String custFName, String custLName, String addr1, String addr2,
			String custPAN, String custAdhar, String custMobile, String custDOB, String custCode);

	public String runDeDup(List<String> parentCustCode, List<String> dedupCustCode);

	List<Customer> getCustomerList(String bankCode);

	List<Customer> getAllCustomer(Customer customer);

	
	
    
    
    
}
