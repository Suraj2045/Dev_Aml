package com.nellinfotech.aml.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.jayway.jsonpath.internal.function.text.Concatenate;
import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.dto.RiskProfile;
import com.nellinfotech.aml.entities.BranchMst;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.entities.Transaction;
//import com.nellinfotech.aml.entities.Account;
import com.nellinfotech.aml.model.Header;
//import com.nellinfotech.aml.repository.AccountRepository;
import com.nellinfotech.aml.repository.BranchMstRepository;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.RiskProfileMstRepository;
import com.nellinfotech.aml.repository.TransactionRepository;
import com.nellinfotech.aml.service.CustomerService;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;

/**
 * @author TUSHAR
 */

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    CustomerRepository customerRpository;
    
    @Autowired
    RiskProfileMstRepository riskProfileMstepository;;
    
    @Autowired
    BranchMstRepository branchMstRepository;
    
//    @Autowired
//    private RiskProfile riskProfile;
    
//    @Autowired
//    AccountRepository accountRepository;
//    
        @Autowired
    DateValidator dateValidator;

		@Override
		public Customer getCustomer(String custCode) {
			try {
				return customerRpository.findByCustCode(custCode);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<Customer> findByCustomerParam(String customerType, String custCode, String customerName, String baseBrCode,
				String countryOfReg, String customerCategory, String nationalId1, String addr1, String city,
				String postalCode, String mobileNo, Double riskManual, String bankCode) {
			List<Customer> dedup= new ArrayList<Customer>();
			String branchName=null;
			String branchCode=null;
			try {
				dedup= customerRpository.findByCustomerParam(customerType, custCode, customerName, countryOfReg, customerCategory, nationalId1, addr1,
						city, postalCode, mobileNo, riskManual, bankCode);
				for (int i = 0; i < dedup.size(); i++) {
					 branchCode=dedup.get(i).getBaseBrCode();
					branchName=branchMstRepository.findBybranchCode(branchCode).getBranchName();
					dedup.get(i).setBranchName(branchName);
					
				}
				return dedup;
				}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public Customer getCustomerMatch(String custCode, String customerFName) {
			// TODO Auto-generated method stub
			return null;
		}

//		@Override
//		public List<Customer> getDueDiligenceParamlist(String bankCode) {
//			try {
//				return customerRpository.findByBankCode(bankCode);
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//		
		@Override
		public List<Customer> getDeDupData(String custCode, String custFName, String custLName, String addr1,
			String addr2, String custPAN, String custAdhar, String custMobile, String custDOB) {
			String fName=null;
			String lName=null;
			String add1=null;
			String add2=null;
			String adharNo=null;
			String pan=null;
			String mobileNo=null;
			Date dob=null;
			String fullName=null;
			String address=null;
			String branchName=null;
			String matchingScore=null;
			String custName=custFName+custLName;
			String custAddress=addr1+addr2;
			try {
				//return customerRpository.findByDeDup(custFName, custLName, addr1, addr2, custPAN, custAdhar, custMobile, custDOB);
				List<Customer> dedup= new ArrayList<Customer>();
				
				dedup=customerRpository.findByDeDup(custCode, custFName, custLName, addr1, addr2, custPAN, custAdhar, custMobile, custDOB);
						
				for (int i = 0; i < dedup.size(); i++) {
				    
				    fName=dedup.get(i).getCustomerFName();
				    lName=dedup.get(i).getCustomerLName();
				    add1=dedup.get(i).getAddr1();
					add2=dedup.get(i).getAddr2();
					mobileNo=dedup.get(i).getMobileNo();
					pan=dedup.get(i).getPanNo();
					adharNo=dedup.get(i).getAadhaarNo();
					dob=dedup.get(i).getCustomerDOB();
					fullName=fName+lName;
					address=add1+add2;
					String branchCode=dedup.get(i).getBaseBrCode();
					branchName=branchMstRepository.findBybranchCode(branchCode).getBranchName();
					
					
					double mobileMatchScore=mobilesimilaritycheck(custMobile, mobileNo);
					double panMatchScore=panSimilaritycheck(custPAN, pan);
					double adharMatchScore=adharSimilaritycheck(custAdhar, adharNo);
					double nameMatchScore=nameSimilarityCheck(fullName, custName)*100;
					double addressMatchScore=addressSimilarityCheck(custAddress, address)*100;
					
					 matchingScore=findHeigestScore(mobileMatchScore, adharMatchScore, nameMatchScore, addressMatchScore, panMatchScore);

					dedup.get(i).setMatchingScore(matchingScore);
					dedup.get(i).setBranchName(branchName);
					
				}
				
				return dedup;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;		
			}
		
		
		@Override
		public String runDeDup(List<String> parentCustCode, List<String> dedupCustCode) {
			 
			try {
				customerRpository.runTransactionDedup(parentCustCode, dedupCustCode);
				customerRpository.runAccountDedup(parentCustCode, dedupCustCode);
				customerRpository.runCustomerDedup(dedupCustCode);
				return ResponseStatus.SUCCESS;
			}
			catch(Exception e) {
				e.printStackTrace();				
			}
			return ResponseStatus.FAILED;
		}
		

    
    /**
     * @author Paresh K
     * @param 
     */
		public static double nameSimilarityCheck(String fullName, String custName) {
			
            String longer = fullName, shorter = custName;
            if (fullName.length() < custName.length()) { // longer should always have greater length
                longer = custName; shorter = fullName;
            }
            int longerLength = longer.length();
            if (longerLength == 0) { return 1.0;  }
            
            return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
     
        }
     
        public static int editDistance(String fullName, String custName) {
            fullName = fullName.toLowerCase();
            custName = custName.toLowerCase();
     
            int[] costs = new int[custName.length() + 1];
            for (int i = 0; i <= fullName.length(); i++) {
                int lastValue = i;
                for (int j = 0; j <= custName.length(); j++) {
                    if (i == 0)
                        costs[j] = j;
                    else {
                        if (j > 0) {
                            int newValue = costs[j - 1];
                            if (fullName.charAt(i - 1) != custName.charAt(j - 1))
                                newValue = Math.min(Math.min(newValue, lastValue),
                                        costs[j]) + 1;
                            costs[j - 1] = lastValue;
                            lastValue = newValue;
                        }
                    }
                }
                if (i > 0)
                    costs[custName.length()] = lastValue;
            }
            return costs[custName.length()];
        }
     

        
/***************************************************************************************************************/
public static double addressSimilarityCheck(String custAddress, String address) {
	//custAddress, address
            String longer = custAddress, shorter = address;
            if (custAddress.length() < address.length()) { 
                longer = address; shorter = custAddress;
            }
            int longerLength = longer.length();
            if (longerLength == 0) { return 1.0;  }
          
            return (longerLength - editAddressDistance(longer, shorter)) / (double) longerLength;
     
        }
     
        public static int editAddressDistance(String custAddress, String address) {
            custAddress = custAddress.toLowerCase();
            address = address.toLowerCase();
     
            int[] costs = new int[address.length() + 1];
            for (int i = 0; i <= custAddress.length(); i++) {
                int lastValue = i;
                for (int j = 0; j <= address.length(); j++) {
                    if (i == 0)
                        costs[j] = j;
                    else {
                        if (j > 0) {
                            int newValue = costs[j - 1];
                            if (custAddress.charAt(i - 1) != address.charAt(j - 1))
                                newValue = Math.min(Math.min(newValue, lastValue),
                                        costs[j]) + 1;
                            costs[j - 1] = lastValue;
                            lastValue = newValue;
                        }
                    }
                }
                if (i > 0)
                    costs[address.length()] = lastValue;
            }
            return costs[address.length()];
        }
     /**************************************************************************************************************/

        public static double mobilesimilaritycheck(String custMobile, String mobileNo) {
        	double mobilesimilaritycheck;
        	if(custMobile.equalsIgnoreCase(mobileNo)){
        		mobilesimilaritycheck=100.00;
        	}
        	else {
        		mobilesimilaritycheck=0.00;
        	}
			return mobilesimilaritycheck;
        	
        }
        
        public static double adharSimilaritycheck(String custAdhar, String adharNo) {
        	double panSimilaritycheck;
        	if(custAdhar.equalsIgnoreCase(adharNo)){
        		panSimilaritycheck=100.00;
        	}
        	else {
        		panSimilaritycheck=0.00;
        	}
			return panSimilaritycheck;
        	
        }
        
        public static double panSimilaritycheck(String custPAN, String pan) {
        	double panSimilaritycheck;
        	if(custPAN.equalsIgnoreCase(pan)){
        		panSimilaritycheck=100.00;
        	}
        	else {
        		panSimilaritycheck=0.00;
        	}
			return panSimilaritycheck;
        	
        }
        
        public static String findHeigestScore(double mobileMatchScore, double adharMatchScore, double nameMatchScore, 
        		double addressMatchScore, double panMatchScore)
        {
        	String matchingScore=null;
        	String mobile=String.format("%.2f", mobileMatchScore).toString();
        	String adhar=String.format("%.2f", adharMatchScore).toString();
        	String pan=String.format("%.2f", panMatchScore).toString();
        	String name=String.format("%.2f", addressMatchScore).toString();
        	String address=String.format("%.2f", nameMatchScore).toString();
            HashMap<String,String>map=new HashMap<String, String>();
            map.put("Adhar:", mobile);
            map.put("Mobile:", adhar);
            map.put("Name:", name);
            map.put("Address:", address);
            map.put("PAN:", pan);
            for (Entry<String, String> entry : map.entrySet()) {
            	
            if(matchingScore==null){
            	matchingScore=entry.getKey()+entry.getValue().toString();
                 }
             
            else {
            	 
            	matchingScore=matchingScore+","+(entry.getKey()+entry.getValue().toString());
                 }
        }
            return matchingScore;
        }

		@Override
		public List<Customer> getCustomerList(String bankCode) {
			try {
				return customerRpository.findByBankCode(bankCode);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<Customer> getAllCustomer(Customer customer) {

			try {
				return 	customerRpository.findAll();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

				
}
     
