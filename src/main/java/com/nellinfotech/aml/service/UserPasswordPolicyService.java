package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.UserPasswordPolicyEntity;
import com.nellinfotech.aml.model.Header;

public interface UserPasswordPolicyService {
    
    List<UserPasswordPolicyEntity> getUserPasswordPolicyByBankCode(String bankCode);
    
    UserPasswordPolicyEntity savePasswordPolicy(Header header, UserPasswordPolicyEntity passwordPlicy);
    
}
