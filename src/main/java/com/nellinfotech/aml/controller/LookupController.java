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
import com.nellinfotech.aml.entities.LookupDtls;
import com.nellinfotech.aml.entities.LookupMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.LookupDtlsService;
import com.nellinfotech.aml.service.LookupMstService;

/**
 * @author ASHWIN
 */
@CrossOrigin(origins = "*")
@RestController
public class LookupController {
    @Autowired
    LookupDtlsService lookupDtlsService;
    
    @Autowired
    LookupMstService lookupMstService;
    
    Logger logger = LoggerFactory.getLogger(LookupController.class);
    
    /**
     * @author ASHWIN
     * @param lookupDtls
     * @return
     */
    @PostMapping("/saveLookupDetails")
    public LookupDtls saveLookupDetails(@RequestHeader HttpHeaders httpHeaders, @RequestBody LookupDtls lookupDtls) {
        Header header = HeaderValidator.headerConversion(httpHeaders);
        return lookupDtlsService.saveLookupDtls(header, lookupDtls);
    }
    
    /**
     * @author ASHWIN
     * @param lookupCode
     * @return
     */
    @GetMapping("/getLookupDtlsByLookupCode")
    public List<LookupDtls> getLookupDtlsByLookupCode(@RequestParam String lookupCode) {
        return lookupDtlsService.getLookupDtlsByLookupCode(lookupCode);
        
    }
    
    /**
     * @author TUSHAR
     * @param lookupMst
     * @return
     */
    @PostMapping("/savelookupmaster")
    public LookupMst saveLookupMaster(@RequestBody LookupMst lookupMst) {
        try {
            logger.info(" inside LookupController - savelookupmaster-" + lookupMst.getLookupCode());
            return lookupMstService.saveLookupMaster(lookupMst);
        } catch (Exception e) {
            logger.error(" inside LookupController - savelookupmaster-" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @return
     */
    @GetMapping("/getlookupmasterlist")
    public List<LookupMst> getLookupMasterList() {
        try {
            logger.info(" inside LookupController - getlookupmasterlist-");
            return lookupMstService.getLookupMasterList();
        } catch (Exception e) {
            logger.error(" inside LookupController - getlookupmasterlist-" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param lookupMst
     * @return
     */
    @PostMapping("/deletelookupDtls")
    public String deletelookupDtls(@RequestBody LookupDtls lookupDtls) {
        try {
            logger.info(" inside LookupController - deletelookupDtls-");
            return lookupDtlsService.deletelookupDtls(lookupDtls);
        } catch (Exception e) {
            logger.error(" inside LookupController - deletelookupDtls-" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param lookupMst
     * @return
     */
    @PostMapping("/updatelookupDtls")
    public LookupDtls updateLookupDtls(@RequestHeader HttpHeaders httpHeaders, @RequestBody LookupDtls lookupDtls) {
        try {
            logger.info(" inside LookupController - updatelookupDtls-");
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return lookupDtlsService.updateLookupDtls(header, lookupDtls);
        } catch (Exception e) {
            logger.error(" inside LookupController - updatelookupDtls-" + e);
        }
        return null;
    }
    
}