package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.LookupMst;

public interface LookupMstRepository extends JpaRepository<LookupMst, Long> {
    @Query(value = " SELECT * FROM lookup_mst WHERE IS_ACTIVE = ?1", nativeQuery = true)
    List<LookupMst> getLookupMasterList(Integer isactive);
    
    @Modifying
    @Query(value = " UPDATE lookup_mst l SET l.IS_ACTIVE=?2 WHERE id = ?1", nativeQuery = true)
    LookupMst deleteLookupMaster(Long id, Integer isActive);
    
    LookupMst findByLookupType(String string);
}
