package com.nellinfotech.aml.service;

import com.nellinfotech.aml.entities.SequenceMst;

/**
 * @author ASHWIN
 */
public interface SequenceMstService {
    
    public SequenceMst saveSequenceMst(SequenceMst sequenceMst);
    
    public SequenceMst getSequenceMstBySeqCode(String seqCode);
    
}