package com.nellinfotech.aml.controller;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.entities.BaseRules;
import com.nellinfotech.aml.entities.RuleException;
import com.nellinfotech.aml.entities.ThresholdRule;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.entities.UIRule;
import com.nellinfotech.aml.service.RuleExcceptionService;
import com.nellinfotech.aml.service.RuleExecutionService;

@CrossOrigin(origins = "*")
@RestController
public class RuleExecustionController {
	@Autowired
	RuleExecutionService ruleExecutionService;
	 @Autowired
	protected DataSource dataSource;
	 
	 @Autowired
	 RuleExcceptionService ruleExcceptionService;
	 
	Logger logger = LoggerFactory.getLogger(RoleMapController.class);
	@PostMapping("/ruleExecution")
	public List<Transaction> ruleExecution(@RequestBody ThresholdRule thresholdRule)
	{
		try
		{
			return ruleExecutionService.ruleExecution(thresholdRule);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Scheduled(cron="0 0 21 1/1 * ?")
	@PostMapping("/ruleExecutionMonthly")
	public String ruleExecutionMonthly()
	{
		try
		{
			return ruleExecutionService.ruleExecutionMonthly();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/procedureSimulation")
	public String procedureSimulation(@RequestParam String procedureName)
	{
		try
		{
			return ruleExecutionService.procedureSimulation(procedureName);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@PostMapping("/ruleExecutionProcedure")
	public String ruleExecutionProcedure()
	{
		try
		{
			return ruleExecutionService.ruleExecutionProcedure();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@PostMapping("/ruleExecutionUI")
	public List<Transaction> ruleExecutionUI(@RequestBody UIRule uiRule)
	{
		try
		{
			return ruleExecutionService.ruleExecutionUI(uiRule);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@PostMapping("/saveBaseRules")
	public BaseRules saveBaseRules(@RequestBody BaseRules baseRules )
	{
		try
		{
			return ruleExecutionService.savebaseRules(baseRules);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping("/getBaseRules")
	public Iterable<BaseRules>  getBaseRules(@RequestParam String bankCode)
	{
		try
		{
			return ruleExecutionService.getBaseRules(bankCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping("/saveThresholdRule")
 	public String saveThresholdRule(@RequestBody ThresholdRule thresholdRule )
	{
		try
		{
			return ruleExecutionService.saveThresholdRule(thresholdRule);
		}catch (Exception e) {
			// TODO: handle exception
		}	
		return null;
	}
	
	@GetMapping("/getThresholdRule")
	public ThresholdRule getThresholdRule(@RequestParam Long baseRuleId,@RequestParam String bankCode)
	{
		try
		{
			return ruleExecutionService.getThresholdRule(baseRuleId,bankCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping("/getBaseRuleById")
	public BaseRules getBaseRuleById(@RequestParam Long id,@RequestParam String bankCode)
	{
		try
		{
			return ruleExecutionService.getBaseRuleById(id,bankCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@GetMapping("/getTablesColumns")
	public Map<String,List<String>> getTablesColumns()
	{
		Map<String,List<String>>listtableColumn=new HashMap<String, List<String>>();
		try
		{	DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
	        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
	        while (tables.next()) {
	        	List<String>columnList=new ArrayList<String>();
	            String tableName=tables.getString("TABLE_NAME"); 
	            ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");
	            while (columns.next()) {
	                String columnName=columns.getString("COLUMN_NAME");
	                columnList.add(columnName);    
	            }
	            listtableColumn.put(tableName, columnList);
	        }
			return listtableColumn;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping("/saveUIRule")
 	public String saveUIRule(@RequestBody UIRule uiRule )
	{
		try
		{
			 
			return ruleExecutionService.saveUIRule(uiRule);
		}catch (Exception e) {
			// TODO: handle exception
		}	
		return null;
	}
	
	
	@GetMapping("/getUIRule")
	public UIRule getUIRule(@RequestParam Long base_id,@RequestParam String bankCode)
	{
		try
		{
			return ruleExecutionService.getUIRule(base_id,bankCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping("/getTransacrion")
	public List<Transaction>getTransaction()
	{
		try
		{
			return ruleExecutionService.getTransacrion();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	@GetMapping("/getBaseUIRule")
	public BaseRules getBaseRuleById()
	{
		try
		{
			return ruleExecutionService.getBaseUIRule();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/saveRuleException")
    public ResponseEntity<Map<String, Object>> saveRuleException(@RequestBody RuleException ruleException )
    {
    	try
    	{
    		return ruleExcceptionService.saveRuleException(ruleException);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }
}
