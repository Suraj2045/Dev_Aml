package com.nellinfotech.aml.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.nellinfotech.aml.entities.DeDupConfig;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.entities.RiskProfileStatus;
import com.nellinfotech.aml.entities.RiskWeightageMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.CustomerService;
import com.nellinfotech.aml.service.HolidayService;
import com.nellinfotech.aml.service.RiskProfileService;

/**
 * @author Paresh K
 */
@CrossOrigin(origins = "*")
@RestController
public class RiskProfileController {
    
    @Autowired
    private RiskProfileService riskProfileService;
    
    Logger logger = LoggerFactory.getLogger(RiskProfileController.class);
        /**
         * 
         * @param custCode
         * @param branchCode
         * @return
         */
	@PostMapping("/getKYCRiskProfile")
	public List<RiskProfileMst> getKYCRiskProfile(@RequestParam String custCode, @RequestParam String branchCode) {
	    try {
	        
	        return riskProfileService.getKYCRiskProfile(custCode, branchCode);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}

	@PostMapping("/getTransactionRiskProfile")
	public List<RiskProfileMst> getTransactionRiskProfile(@RequestParam String custCode, @RequestParam String branchCode) {
	    try {
	        
	        return riskProfileService.getTransactionRiskProfile(custCode, branchCode);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}

	
	@PostMapping("/getTransTrendRiskProfile")
	public List<RiskProfileMst> getTransTrendRiskProfile(@RequestParam String custCode, @RequestParam String branchCode) {
	    try {
	        
	        return riskProfileService.getTransTrendRiskProfile(custCode, branchCode);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}

	@PostMapping("/getViolationRiskProfile")
	public List<RiskProfileMst> getViolationRiskProfile(@RequestParam String custCode, @RequestParam String branchCode) {
	    try {
	        
	        return riskProfileService.getViolationRiskProfile(custCode, branchCode);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}
	
	@PostMapping("/getRiskGraphData")
	public List<RiskProfileStatus> getRiskGraphData(@RequestParam String custCode, @RequestParam String branchCode) {
	    try {
	        
	        return riskProfileService.getRiskGraphData(custCode, branchCode);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}
	
	
	@PostMapping("/getRiskWightageData")
	public List<RiskWeightageMst> getRiskWightageData(@RequestParam String bankCode) {
	    try {
	        
	        return riskProfileService.getRiskWightageData(bankCode);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}
	
	@PostMapping("/getRiskMovement")
	public List<RiskProfileStatus> getRiskMovement(@RequestParam String custCode, @RequestParam String fromDate, @RequestParam String toDate) {
//		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//	     String from = dateFormatter.format(fromDate);
//	     String to = dateFormatter.format(toDate);
//	     
	    try {
	        
	        return riskProfileService.getRiskMovement(custCode, fromDate, toDate);
	      
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}
	
	@PostMapping("/updateRiskScore")
	public String updateRiskScore(@RequestParam String custCode, @RequestParam String branchCode) {
	    try {
	        
	         riskProfileService.updateRiskScore(custCode, branchCode);
	      return "Risk Score updated";
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}
	
	@PostMapping("/updateRiskProfile")
	public String updateRiskProfile(@RequestParam String baseBrCode) {
	    try {
	        
	         riskProfileService.updateRiskProfile(baseBrCode);
	      return "Risk Score updated";
	    } catch (Exception e) {
	        logger.error("Error in RiskProfile controller" + e);
	    }
	    return null;
		}
	
    /**
     * @author Ravi
     * @param httpHeaders
     * @param branchMst
     * @return
     */
    @PostMapping("/updateRiskWeightage")
    public String updateRiskWeightage(@RequestHeader HttpHeaders httpHeaders, @RequestBody List<RiskWeightageMst> riskWeightageMst) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
             riskProfileService.updateRiskWeightage(header, riskWeightageMst);
             return "Risk Weightage Updated";
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
