package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.LookupDtls;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.LookupDtlsRepository;
import com.nellinfotech.aml.service.LookupDtlsService;

/**
 * @author ASHWIN
 */

@Service
public class LookupDtlsServiceImpl implements LookupDtlsService {
    
    @Autowired
    LookupDtlsRepository lookupDtlsRepository;
    
    @Autowired
    DateValidator dateValidator;
    
    /**
     * @author ASHWIN
     * @param lookupDtls
     */
    @Override
    public LookupDtls saveLookupDtls(Header header, LookupDtls lookupDtls) {
        Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
        lookupDtls.setBankCode(header.getBankCode());
        lookupDtls.setCreatedBy(header.getUserId());
        lookupDtls.setCreatedDate(currentDate);
        return lookupDtlsRepository.save(lookupDtls);
    }
    
    /**
     * @author ASHWIN
     * @param lookupCode
     */
    @Override
    public List<LookupDtls> getLookupDtlsByLookupCode(String lookupCode) {
        Integer isActive = 1;
        return lookupDtlsRepository.findByLookupCode(lookupCode, isActive);
    }
    
    @Override
    public String deletelookupDtls(LookupDtls lookupDtls) {
        try {
            LookupDtls existingLookupDtls = lookupDtlsRepository.findById(lookupDtls.getId()).orElse(null);
            existingLookupDtls.setIsActive(0);
            lookupDtlsRepository.save(existingLookupDtls);
            return ResponseStatus.SUCCESS;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public LookupDtls updateLookupDtls(Header header, LookupDtls lookupDtls) {
        try {
            LookupDtls existingLookupDtls = lookupDtlsRepository.findById(lookupDtls.getId()).orElse(null);
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            existingLookupDtls.setLookupCode(lookupDtls.getLookupCode());
            existingLookupDtls.setLookupValue(lookupDtls.getLookupValue());
            existingLookupDtls.setLookupValueCode(lookupDtls.getLookupValueCode());
            existingLookupDtls.setLookupAlias(lookupDtls.getLookupAlias());
            existingLookupDtls.setModifiedBy(header.getUserId());
            existingLookupDtls.setModifiedDate(currentDate);
            return lookupDtlsRepository.save(existingLookupDtls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}