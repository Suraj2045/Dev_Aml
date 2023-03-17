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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.dto.AlertStatistics;
import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.BranchParam;
import com.nellinfotech.aml.entities.Case;
import com.nellinfotech.aml.model.Header;

import com.nellinfotech.aml.service.CaseService;
/**
 * @author Tushar
 */
@CrossOrigin(origins = "*")
@RestController
public class CaseController {
    @Autowired
    CaseService caseService;
    
    Logger logger = LoggerFactory.getLogger(Case.class);
    
    /**
     * @author Tushar
     * @param branchParam
     * @return
     */
    
    @PostMapping("/saveCase")
    public Case saveCase(@RequestHeader HttpHeaders httpHeaders, @RequestBody Case amlCase) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return caseService.saveCase(header, amlCase);
        } catch (Exception e) {
            logger.error("Casecontroller  - saveCase" + e);
        }
        return null;
    }
    
    @PostMapping("/getCaseList")
    public List<Case> getCaseList(@RequestHeader HttpHeaders httpHeaders,@RequestParam String toDate, String fromDate, String alertType) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.getCaseList(bankCode, toDate, fromDate, alertType);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}
    
    @PostMapping("/getCase")
    public List<Case> getCase(@RequestHeader HttpHeaders httpHeaders, String caseID) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.getCase(bankCode, caseID);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}
    
  

    @PostMapping("/getDashboardCaseList")
    public List<Case> getDashboardCaseList(@RequestHeader HttpHeaders httpHeaders,@RequestParam String toDate, @RequestParam String fromDate, @RequestParam String alertType,
    		@RequestParam String alertSubCode, @RequestParam String customerRisk) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.getDashboardCaseList(bankCode, toDate, fromDate, alertType, alertSubCode, customerRisk);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}
    
    @PostMapping("/getCaseListByCustomerID")
    public List<Case> getCaseListByCustomerID(@RequestHeader HttpHeaders httpHeaders,@RequestParam String custCode) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.getCaseListByCustomerID(bankCode, custCode);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}

    @PostMapping("/getCaseListByCaseID")
    public List<Case> getCaseListByCaseID(@RequestHeader HttpHeaders httpHeaders,@RequestParam String caseID) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.getCaseListByCaseID(bankCode, caseID);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}

    @PostMapping("/getConfirmedFraudList")
    public List<Case> getConfirmedFraudList(@RequestHeader HttpHeaders httpHeaders,@RequestParam String toDate, String fromDate, String operator, String amount) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.getConfirmedFraudList(bankCode, toDate, fromDate, operator, amount);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}

    
    @PostMapping("/searchCase")
    public List<Case> searchCase(@RequestHeader HttpHeaders httpHeaders,@RequestParam String toDate, String fromDate, String caseID, String creatorName, 
    		String customerName, String customerNumber, String status) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.searchCase(bankCode, toDate, fromDate, caseID, creatorName, customerName, customerNumber, status);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}
    
    @PostMapping("/updateCaseStatus")
    public List<Case> updateCaseStatus(@RequestHeader HttpHeaders httpHeaders,@RequestBody List<Case> amlCase) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.updateCaseStatus(header, amlCase);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}
    
    @PostMapping("/updateConfirmCase")
    public List<Case> updateConfirmFraud(@RequestHeader HttpHeaders httpHeaders,@RequestBody List<Case> amlCase) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.updateConfirmFraud(header, amlCase);
    	  
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	    
    	}
    
    @GetMapping("/alertStatistics")
    public List<AlertStatistics>  alertStatistics(@RequestHeader HttpHeaders httpHeaders) {
    	try {
    		Header header = HeaderValidator.headerConversion(httpHeaders);
    		String bankCode=header.getBankCode();
    	    return caseService.alertStatistics(header);	 
    	} catch (Exception e) {
    	    logger.error("Error in Case controller" + e);
    	}
    	return null;
    	   
    	}

}
