package com.nellinfotech.aml.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nellinfotech.aml.entities.UIRule;

public interface UIRuleRepository extends CrudRepository<UIRule, Long>{

	UIRule findByBaseRuleIdAndBankCodeAndStatus(Long base_id, String bankCode, String status);

	UIRule findByBaseRuleIdAndBankCode(Long baseRuleId, String bankCode);

	

	

	

	

}
