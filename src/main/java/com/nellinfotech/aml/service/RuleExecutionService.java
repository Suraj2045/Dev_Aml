package com.nellinfotech.aml.service;

import java.util.List;
import java.util.Map;

import com.nellinfotech.aml.entities.BaseRules;
import com.nellinfotech.aml.entities.ThresholdRule;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.entities.UIRule;

public interface RuleExecutionService {

	List<Transaction> ruleExecution(ThresholdRule thresholdRule);

	String ruleExecutionMonthly();

	BaseRules savebaseRules(BaseRules baseRules);

	Iterable<BaseRules> getBaseRules(String bankCode);

	String saveThresholdRule(ThresholdRule thresholdRule);

	ThresholdRule getThresholdRule(Long baseRuleId, String bankCode);

	BaseRules getBaseRuleById(Long id, String bankCode);

	String ruleExecutionProcedure();

	List<Transaction> ruleExecutionUI(UIRule uiRule);

	String procedureSimulation(String procedureName);

	String saveUIRule(UIRule uiRule);

	UIRule getUIRule(Long base_id, String bankCode);

	List<Transaction> getTransacrion();

	BaseRules getBaseUIRule();


	

}
