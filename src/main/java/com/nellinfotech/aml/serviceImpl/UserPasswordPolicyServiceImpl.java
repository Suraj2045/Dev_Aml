package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.entities.UserPasswordPolicyEntity;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.UserPasswordPolicyRepository;
import com.nellinfotech.aml.service.UserPasswordPolicyService;

@Service
@Scope("prototype")
public class UserPasswordPolicyServiceImpl implements UserPasswordPolicyService {
    
    @Autowired
    UserPasswordPolicyRepository userPasswordPolicyRepository;
    @Autowired
    DateValidator dateValidator;
    
    @Override
    public List<UserPasswordPolicyEntity> getUserPasswordPolicyByBankCode(String bankCode) {
        
        return userPasswordPolicyRepository.findByBankCode(bankCode);
    }
    
    @Override
    public UserPasswordPolicyEntity savePasswordPolicy(Header header, UserPasswordPolicyEntity passwordPlicy) {
        
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            passwordPlicy.setBankCode(header.getBankCode());
            passwordPlicy.setCreatedBy(header.getUserId());
            passwordPlicy.setCreatedDate(currentDate);
            return userPasswordPolicyRepository.save(passwordPlicy);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
