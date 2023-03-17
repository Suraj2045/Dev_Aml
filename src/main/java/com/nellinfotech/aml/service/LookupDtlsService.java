package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.LookupDtls;
import com.nellinfotech.aml.model.Header;

/**
 * @author ASHWIN
 */
public interface LookupDtlsService {
    
    public LookupDtls saveLookupDtls(Header header, LookupDtls lookupDtls);
    
    public List<LookupDtls> getLookupDtlsByLookupCode(String lookupCode);
    
    public String deletelookupDtls(LookupDtls lookupDtls);
    
    public LookupDtls updateLookupDtls(Header header, LookupDtls lookupDtls);
    
}