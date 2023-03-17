package com.nellinfotech.aml.constant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nellinfotech.aml.entities.LookupDtls;
import com.nellinfotech.aml.entities.LookupMst;
import com.nellinfotech.aml.repository.LookupDtlsRepository;
import com.nellinfotech.aml.repository.LookupMstRepository;

public class LoadMasters {
    @Autowired
    LookupMstRepository lookupMstRepo;
    @Autowired
    LookupDtlsRepository lookupDtlsRepo;
    
    public List<LookupDtls> getLookupDetails(String type) {
        try {
            
            List<LookupMst> lookup = new ArrayList<LookupMst>();
            List<LookupDtls> lookupDtls = new ArrayList<LookupDtls>();
            List<LookupDtls> lookupdetails = new ArrayList<LookupDtls>();
            lookup = lookupMstRepo.findAll();
            lookupDtls = lookupDtlsRepo.findAll();
            
            for (LookupMst lookupmst : lookup) {
                if (lookupmst.getLookupType().equals(type)) {
                    for (LookupDtls lookupDtls2 : lookupDtls) {
                        
                        if (lookupmst.getLookupCode().equals(lookupDtls2.getLookupCode())) {
                            lookupdetails.add(lookupDtls2);
                        }
                    }
                }
            }
            
            return lookupdetails;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return null;
    }
    
}
