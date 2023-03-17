package com.nellinfotech.aml.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nellinfotech.aml.entities.BaseRules;

@Repository
public interface BaseRuleRepository extends CrudRepository<BaseRules, Long>{

	Iterable<BaseRules> findAllByBankCode(String bankCode);
	@Transactional
	@Modifying
	@Query(value=" update baserules b set  b.bank_code=:bankCode,b.customer=:customer,b.scenario_description=:scenarioDescription,b.sevrity=:sevrity,b.is_active=:isActive,b.version=:version,b.type=:type,b.status=:status,b.modified_date=:modifiedDate,b.modified_by=:modifiedBy,b.created_date=:createdDate,b.created_by=:createdBy,b.auth_status=:authStatus   where b.id=:id ",nativeQuery=true)
	void updateRule(String bankCode, String customer, String scenarioDescription, String sevrity,
			Integer isActive, Integer version, String type, String status, Date modifiedDate, String modifiedBy,
			Date createdDate, String createdBy, Integer authStatus,Long id);
	
	@Query(value = " SELECT * FROM baserules  WHERE id=:id and bank_code=:bankCode ", nativeQuery = true)
	BaseRules getBaseRuleById(Long id, String bankCode);
	
	
	BaseRules findByType(String type);

	

}
