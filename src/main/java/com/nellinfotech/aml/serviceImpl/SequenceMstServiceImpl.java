package com.nellinfotech.aml.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.entities.SequenceMst;
import com.nellinfotech.aml.repository.SequenceMstRepository;
import com.nellinfotech.aml.service.SequenceMstService;

/**
 * @author ASHWIN
 */

@Service
public class SequenceMstServiceImpl implements SequenceMstService {
    
    @Autowired
    SequenceMstRepository sequenceMstRepository;
    
    /**
     * @author ASHWIN
     * @param sequenceMst
     */
    @Override
    public SequenceMst saveSequenceMst(SequenceMst sequenceMst) {
        return sequenceMstRepository.save(sequenceMst);
    }
    
    /**
     * @author ASHWIN
     * @param seqCode
     */
    @Override
    public SequenceMst getSequenceMstBySeqCode(String seqCode) {
        return sequenceMstRepository.findBySeqCode(seqCode);
    }
    
}