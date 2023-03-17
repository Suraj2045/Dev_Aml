package com.nellinfotech.aml.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.LoginService;
import com.nellinfotech.aml.serviceImpl.LoginServiceImpl;

/**
 * @author ASHWIN
 */
@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    
    @Autowired
    LoginService loginService;
    
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    
    /**
     * @author TUSHAR
     * @param user
     * @return
     */
    @PostMapping("/userlogin")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody UserMst user) {
     
            
            ResponseEntity<Map<String, Object>> userDetail = null;
            return userDetail = loginService.userLogin(user);
        
    }
    
    /**
     * @author ASHWIN
     * @param map
     * @return
     */
    @PostMapping("/changeCredentials")
    public ResponseEntity<Map<String, Object>> changeCredentials(@RequestBody Map<String, String> map) {
        logger.info("In LoginController --- changeCredentials()");
        try {
            loginService.changeCredentials(map);
        } catch (Exception e) {
            
            // TODO: handle exception
        }
        return null;
    }
    
    @PostMapping("/userLock")
    public String userLock(@RequestBody UserMst user) {
    	String result="";
        try {
             result=loginService.userLock(user);
        } catch (Exception e) {
            
            // TODO: handle exception
        }
        return result;
    }
    
    @GetMapping("/checkPasswordPolicy")
    public String checkPasswordPolicy(@RequestHeader HttpHeaders httpHeaders, @RequestParam String pwd) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return loginService.checkPasswordPolicy(header, pwd);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}