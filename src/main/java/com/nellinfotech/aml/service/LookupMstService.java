package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.LookupMst;

public interface LookupMstService {
    
    LookupMst saveLookupMaster(LookupMst lookupMst);
    
    List<LookupMst> getLookupMasterList();
    
}
