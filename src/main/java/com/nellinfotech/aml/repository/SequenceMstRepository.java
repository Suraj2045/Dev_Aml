package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.SequenceMst;

/**
 * @author ASHWIN
 */
public interface SequenceMstRepository extends JpaRepository<SequenceMst, Long> {
    
    public SequenceMst findBySeqCode(String seqCode);
    
}