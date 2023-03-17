package com.nellinfotech.aml.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.model.Header;

public interface LoginService {
    
    public ResponseEntity<Map<String, Object>> userLogin(UserMst user);
    
    public String changeCredentials(Map<String, String> map);
    
    public String checkPasswordPolicy(Header header, String pwd);

	String userLock(UserMst user);
}
