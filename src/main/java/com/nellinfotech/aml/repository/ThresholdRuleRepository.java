package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nellinfotech.aml.entities.ThresholdRule;

@Repository
public interface ThresholdRuleRepository extends CrudRepository<ThresholdRule,Long> {

	ThresholdRule findByBaseRuleIdAndBankCode(Long baseRuleId, String bankCode);

	List<ThresholdRule> findAllByFrequence(String string);


}
