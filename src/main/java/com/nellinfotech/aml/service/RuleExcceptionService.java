package com.nellinfotech.aml.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nellinfotech.aml.entities.RuleException;

public interface RuleExcceptionService {

	ResponseEntity<Map<String, Object>> saveRuleException(RuleException ruleException);

}
