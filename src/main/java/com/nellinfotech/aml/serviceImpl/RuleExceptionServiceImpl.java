package com.nellinfotech.aml.serviceImpl;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.entities.RuleException;
import com.nellinfotech.aml.repository.RuleExceptionRepository;
import com.nellinfotech.aml.service.RuleExcceptionService;

@Service
public class RuleExceptionServiceImpl implements RuleExcceptionService {

	@Autowired
	  RuleExceptionRepository ruleExceptionrepository;
		
		@Override
		public ResponseEntity<Map<String, Object>> saveRuleException(RuleException ruleException) {
			 ResponseEntity<Map<String, Object>> result=null;
			 	String msg="save Successfully";
			 	String fail="fail";
			 	 Map<String,Object> res =new HashedMap<String, Object>();
				 HttpHeaders headers = new HttpHeaders();
			try
			{
				 ruleExceptionrepository.save(ruleException);
				 res.put("mesg", msg);
				 return result = (new ResponseEntity<Map<String, Object>>(res,headers , HttpStatus.OK));
			}catch (Exception e) {
				 res.put("mesg", fail);
				 return result = (new ResponseEntity<Map<String, Object>>(res,headers , HttpStatus.EXPECTATION_FAILED));
			}
			
			
		}
}
