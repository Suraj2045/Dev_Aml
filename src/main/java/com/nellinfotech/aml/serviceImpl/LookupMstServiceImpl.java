package com.nellinfotech.aml.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.entities.LookupMst;
import com.nellinfotech.aml.repository.LookupMstRepository;
import com.nellinfotech.aml.service.LookupMstService;

/**
 * @author TUSHAR
 */
@Service
public class LookupMstServiceImpl implements LookupMstService {
    @Autowired
    LookupMstRepository lookupMstRepository;
    
    /**
     * @author TUSHAR
     * @param lookupMst
     *            return
     */
    @Override
    public LookupMst saveLookupMaster(LookupMst lookupMst) {
        try {
            return lookupMstRepository.save(lookupMst);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author TUSHAR return
     */
    @Override
    public List<LookupMst> getLookupMasterList() {
        try {
            Integer isactive = 1;
            return lookupMstRepository.getLookupMasterList(isactive);
            //return lookupMstRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
