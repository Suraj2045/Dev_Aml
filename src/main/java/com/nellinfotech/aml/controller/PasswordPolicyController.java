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
import com.nellinfotech.aml.entities.UserPasswordPolicyEntity;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.UserPasswordPolicyService;

@CrossOrigin(origins = "*")
@RestController
public class PasswordPolicyController {
    
    @Autowired
    private UserPasswordPolicyService userPasswordPolicyService;
    
    Logger logger = LoggerFactory.getLogger(PasswordPolicyController.class);
    
    @PostMapping("/savePasswordPolicy")
    public UserPasswordPolicyEntity savePasswordPolicy(@RequestHeader HttpHeaders httpHeaders,
            @RequestBody UserPasswordPolicyEntity passwordPlicy) {
        try {
            logger.info(" inside PasswordPolicyController - savePasswordPolicy-");
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return userPasswordPolicyService.savePasswordPolicy(header, passwordPlicy);
        } catch (Exception e) {
            logger.error("PasswordPolicyController  savePasswordPolicy" + e);
        }
        return null;
    }
    
    @GetMapping("/getPasswordPolicy")
    public List<UserPasswordPolicyEntity> getUserPasswordPolicyByBankCode(@RequestParam String bankCode) {
        try {
            logger.info(" inside PasswordPolicyController - savePasswordPolicy-");
            return userPasswordPolicyService.getUserPasswordPolicyByBankCode(bankCode);
            
        } catch (Exception e) {
            logger.error("PasswordPolicyController  getPasswordPolicy" + e);
        }
        return null;
    }
}
