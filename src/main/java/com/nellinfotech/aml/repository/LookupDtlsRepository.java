package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.LookupDtls;

/**
 * @author ASHWIN
 */
public interface LookupDtlsRepository extends JpaRepository<LookupDtls, Long> {
    @Query(value = " SELECT * FROM lookup_details WHERE lookup_code=?1 AND IS_ACTIVE = ?2", nativeQuery = true)
    public List<LookupDtls> findByLookupCode(String lookupCode, Integer isActive);
    
}