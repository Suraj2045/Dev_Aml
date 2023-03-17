package com.nellinfotech.aml.serviceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.RuleExecution.TransactionRuleExecution;
import com.nellinfotech.aml.entities.Account;
import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.BaseRules;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.ThresholdRule;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.entities.UIRule;
import com.nellinfotech.aml.repository.AccountRepository;
import com.nellinfotech.aml.repository.AlertRepository;
import com.nellinfotech.aml.repository.BaseRuleRepository;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.ThresholdRuleRepository;
import com.nellinfotech.aml.repository.TransactionRepository;
import com.nellinfotech.aml.repository.UIRuleRepository;
import com.nellinfotech.aml.service.RuleExecutionService;

@Service
public class RuleExecutionServiceImpl implements RuleExecutionService
{
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired 
	AlertRepository alertRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BaseRuleRepository baseRuleRepository;
	@Autowired
	ThresholdRuleRepository thresholdRuleRepository;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UIRuleRepository uiRuleRepository;
	
	///create threshold rules and execute for test
	@Override
	public List<Transaction> ruleExecution(ThresholdRule thresholdRule) {
		List<Transaction>transaction=new ArrayList<Transaction>();
		List<Account>account=new ArrayList<Account>();
		List<Customer>customer=new ArrayList<Customer>();		
		account=accountRepository.findAll();
		customer=customerRepository.findAll();
		TransactionRuleExecution ruleExe=new TransactionRuleExecution();
		try
		{	
		Map<String,String>traMap=new HashMap<String,String>();
		if(thresholdRule.getFrequence().contentEquals("Daily"))
		{	System.out.println("Daily");
	
		transaction=transactionRepository.findDailyRecords(thresholdRule.getBankCode(),thresholdRule.getTxn_amt(),thresholdRule.getTxn_type(),thresholdRule.getDr_cr());
			List<Transaction>trans=checkRules(transaction,customer,thresholdRule.getCust_type());
			return trans;
			
		}
		if(thresholdRule.getFrequence().contentEquals("Weekly"))
		{
			System.out.println("Weekly");
			transaction=transactionRepository.findWeeklyRecords(thresholdRule.getBankCode(),thresholdRule.getTxn_amt(),thresholdRule.getTxn_type(),thresholdRule.getDr_cr());
			List<Transaction>trans=checkRules(transaction,customer,thresholdRule.getCust_type());
			return trans;
		}
		if(thresholdRule.getFrequence().contentEquals("Monthly"))
		{ 
			System.out.println("Monthly");
			transaction=transactionRepository.findMonthlyRecords(thresholdRule.getBankCode(),thresholdRule.getTxn_amt(),thresholdRule.getTxn_type(),thresholdRule.getDr_cr());
			List<Transaction>trans=checkRules(transaction,customer,thresholdRule.getCust_type());
			return trans;
		}
		if(thresholdRule.getFrequence().contentEquals("Quarterly"))
		{	System.out.println("Quarterly");
			transaction=transactionRepository.findQuarterlyRecords(thresholdRule.getBankCode(),thresholdRule.getTxn_amt(),thresholdRule.getTxn_type(),thresholdRule.getDr_cr());
			List<Transaction>trans=checkRules(transaction,customer,thresholdRule.getCust_type());
			return trans;
		}
		if(thresholdRule.getFrequence().contentEquals("Yearly"))
		{	System.out.println("Yearly");
			transaction=transactionRepository.findYearlyRecords(thresholdRule.getBankCode(),thresholdRule.getTxn_amt(),thresholdRule.getTxn_type(),thresholdRule.getDr_cr());
			List<Transaction>trans=checkRules(transaction,customer,thresholdRule.getCust_type());
			return trans;
			
		}
		
			//transaction=transactionRepository.findRecords();	
			transaction.sort((Transaction s1, Transaction s2)->s1.getAcctNo().compareTo(s2.getAcctNo()));
			for (Transaction transaction2 : transaction) {
				traMap.put(transaction2.getAcctNo(), transaction2.getAcctNo());
			}
			List<String> lt = new ArrayList<String>(traMap.keySet());		
			for (String acnot : lt) {
				List<Transaction>tt=new ArrayList<Transaction>();
				for (Transaction transaction2 : transaction) {
					if(acnot.contentEquals(transaction2.getAcctNo()))
					{
						tt.add(transaction2);
					}	
				}		
				Alert alert=new Alert();
				List<String> msg=ruleExe.prepareRules(acnot,tt);	
				Account accountDetails=new Account();
				Customer customerData=new Customer();
				for (Account ac : account) {
					if(ac.getAcctNo().contentEquals(acnot))
					{
						accountDetails=ac;
					}					
				}
				for (Customer custDetails : customer) {
					
					if(custDetails.getCustCode().contentEquals(accountDetails.getCustCode()))
					{
						customerData=custDetails;
					}
				}
				for (String message : msg) {
					if(!message.contentEquals("ok"))
					{	  alert.setAccountNo(acnot);
		      			  alert.setAssignedUser("Stive");
		      			  alert.setCustomerName(customerData.getCustomerFName());
		      			  alert.setCustId(customerData.getCustCode());
		      			  alert.setCustomerNo(customerData.getCustCode());
		      			  alert.setAlertStatus(message);
		      			  if(accountDetails!=null)
		      			  {
		      			   alert.setCustomerName(accountDetails.getCustCode());
		      			   alert.setBankCode(accountDetails.getBankCode());	
		      			  }
		      			   alert.setRiskSeverity(customerData.getRemarks());
		      			  alertRepository.save(alert);
					}
				}
				
			}	
		//return "success";
		}catch (Exception e) {
			
		}
			return null;
	}
	
	private List<Transaction> checkRules(List<Transaction> transaction, List<Customer> customer,String cust_type) {
		List<Transaction>transactionList=new ArrayList<Transaction>();
		for (Transaction trans : transaction) 
		{	for (Customer cust : customer) 
			{ if(trans.getCustCode().contentEquals(cust.getCustCode())&&cust.getCustomerType().contentEquals(cust_type))
				{	 
					  transactionList.add(trans);
				}
			}
		}
		return transactionList;	
	}
///Scheduler threshold rule execution
	@Override
	public String ruleExecutionMonthly() {
		List<Customer>customer=new ArrayList<Customer>();		
		customer=customerRepository.findAll();
		List<ThresholdRule>listBaseRules=thresholdRuleRepository.findAllByFrequence("Yearly");
		try
		{	if(listBaseRules.size()>0&&listBaseRules!=null)
		  {
			for (ThresholdRule thresholdRule : listBaseRules) 
			{
				if(thresholdRule.getFrequence().contentEquals("Yearly")&&thresholdRule.getTxn_amt()>0)
				{	System.out.println("Daily shedule job rule");
			
				List<Transaction>transaction=transactionRepository.findDailyRecords(thresholdRule.getBankCode(),thresholdRule.getTxn_amt(),thresholdRule.getTxn_type(),thresholdRule.getDr_cr());
				
					if(transaction.size()>0&&transaction.isEmpty()!=true) {
						String message=createVRVAlert(transaction,customer,thresholdRule.getCust_type(),thresholdRule.getBaseRuleId());					System.out.println(message);
					return message;
					}
					else
					{
						System.out.println("transaction not found");
						return "transaction not found";
					}
				}
				
			}	
		  }
		else
		{
			return "Threshold Rules not found";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
			return null;
	}

	private String createVRVAlert(List<Transaction> transaction, List<Customer> customer, String cust_type,Long BaseRuleId) {
		try
		{   List<Alert>alertList=new ArrayList<Alert>();
			for (Transaction trans : transaction)
			{	for (Customer cust : customer)
				{ if(cust_type!=null)
				{
				if(trans.getCustCode().contentEquals(cust.getCustCode())&&cust.getCustomerType().contentEquals(cust_type))
					{	  Alert alert=new Alert();
						  alert.setAccountNo(trans.getAcctNo());
		      			  alert.setCustomerName(cust.getCustomerFName());
		      			  alert.setCustId(cust.getCustCode());
		      			  alert.setCustomerNo(cust.getCustCode());
		      			  alert.setAlertCode("vrv");
		      			  alert.setAlertStatus("rule override");
		      			  if(cust!=null)
		      			  {
		      			   alert.setCustomerName(cust.getCustCode());
		      			   alert.setBankCode(cust.getBankCode());	
		      			  }
		      			 alert.setBaseRuleId(BaseRuleId);
		      		    alert.setTxnAmount(trans.getTxnAmount());
		      			alert.setTxnDtTm(trans.getTxnDtTm());
		      			alert.setCashflowType(trans.getCashflowType());
		      			alert.setTxnNo(trans.getTxnNo());
		      			alert.setTxnType(trans.getTxnType());
		      			alert.setRiskSeverity(cust.getRemarks());
		      			alertList.add(alert);
					}
				}
				else
				{
					return "rule customer type is null";
				}
				}
			}
			if(alertList.size()>0)
			alertRepository.saveAll(alertList);
			return "Success";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "Fail";
	}
	
	@Override
	public BaseRules savebaseRules(BaseRules baseRules) {
		BaseRules brule=new BaseRules();
		try
		{		
			if(baseRules.getId()==null)
			{
				brule=baseRuleRepository.save(baseRules);
				return brule;
			}
			else
			{
			
				baseRuleRepository.updateRule(baseRules.getBankCode(),baseRules.getCustomer(),baseRules.getScenarioDescription(),baseRules.getSevrity(),baseRules.getIsActive(),baseRules.getVersion(),baseRules.getType(),baseRules.getStatus(),baseRules.getModifiedDate(),baseRules.getModifiedBy(),baseRules.getCreatedDate(),baseRules.getCreatedBy(),baseRules.getAuthStatus(),baseRules.getId());
				return baseRules;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return brule;
	}

	@Override
	public Iterable<BaseRules> getBaseRules(String bankCode) {
		Iterable<BaseRules> obj=null;
		try
		{
			return baseRuleRepository.findAllByBankCode(bankCode);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}


	@Override
	public String saveThresholdRule(ThresholdRule thresholdRule) {
		try
		{
			thresholdRuleRepository.save(thresholdRule);
			return "threshold rule save successfuly";
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ThresholdRule getThresholdRule(Long baseRuleId,String bankCode) {
		try
		{
			return thresholdRuleRepository.findByBaseRuleIdAndBankCode(baseRuleId,bankCode);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}



	@Override
	public BaseRules getBaseRuleById(Long id, String bankCode) {
		try
		{
			return baseRuleRepository.getBaseRuleById(id, bankCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
///procedure rule execution
	@Transactional
	@Override
	public String ruleExecutionProcedure() {
		try
		{
			
			////////
			SimpleJdbcCall simpleJdbcCall = null;
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("checkTransaction");
//			Map<String, Object> inParamMap = new HashMap<String, Object>();
//			inParamMap.put("keyword", inputData.getKeyword());
//			inParamMap.put("sortBy", inputData.getSortBy());
//			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object>simpleJdbcCallResult = simpleJdbcCall.execute();
			return "procedure executed successfully";
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
///UI Rule Execution Service
	@Transactional
	@Override
	public List<Transaction> ruleExecutionUI(UIRule uiRule) {
			List<Transaction> list = new ArrayList<Transaction>();
		    String queryString="";	
			try {
				 final String REGEX = "\\sum";
				 String arr[]= {"sum","avg","count"};
				 int count = 0;
				 for(int i=0;i<arr.length;i++)
				 { Pattern p = Pattern.compile(arr[i],Pattern.CASE_INSENSITIVE);
					 Matcher m = p.matcher(uiRule.getUi_Made_Rule());   // get a matcher object
					 while(m.find()) {
				       count++;       
				      }
				 }  
				 if(count>0)
				 {
					 queryString=" SELECT id,txn_amt as txnAmount,auth_status,bank_code,created_by,created_date,is_active,modified_by, " + 
					 		" modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee, " + 
					 		" txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code," + 
					 		" txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel "
					 		+ " from transaction  group by acct_no HAVING "+uiRule.getUi_Made_Rule();//"SELECT * FROM Transaction where txn_amt>10000 "//acct_no HAVING SUM(txn_amt) >sum(ac_bal_amt)
				 }
				 else
				 {
					 queryString=" SELECT id,txn_amt as txnAmount,auth_status,bank_code,created_by,created_date,is_active,modified_by, " + 
					 		"modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee, " + 
					 		"txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code, " + 
					 		"txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel "
					 		+ " from transaction where "+uiRule.getUi_Made_Rule();
				 }
				list = jdbcTemplate.query(queryString,new BeanPropertyRowMapper<Transaction>(Transaction.class));
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				
				
			}
		return null;
	}

	@Override
	public String procedureSimulation(String procedureName) {
		try
		{////////
			SimpleJdbcCall simpleJdbcCall = null;
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procedureName);
//			Map<String, Object> inParamMap = new HashMap<String, Object>();
//			inParamMap.put("keyword", inputData.getKeyword());
//			inParamMap.put("sortBy", inputData.getSortBy());
//			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object>simpleJdbcCallResult = simpleJdbcCall.execute();
			return "procedure executed successfully";
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public String saveUIRule(UIRule uiRule) {
		
		try
		{
		if(uiRule.getId()==null)
		{
			uiRuleRepository.save(uiRule);
			return"save Rule Successfully";
		}
		else
		{
			
			UIRule uiRule1=uiRuleRepository.findByBaseRuleIdAndBankCode(uiRule.getBaseRuleId(), uiRule.getBankCode());
			uiRule1.setSevrity(uiRule.getSevrity());
			uiRule1.setStatus(uiRule.getStatus());
			uiRule1.setFrequence(uiRule.getFrequence());
			uiRule1.setUi_Made_Rule(uiRule.getUi_Made_Rule());
			uiRuleRepository.save(uiRule1);
			
			//uiRuleRepository.updateUIRule(uiRule.getAuthStatus(),uiRule.getBankCode(),uiRule.getBaseRuleId(),uiRule.getCreatedBy(),uiRule.getCreatedDate(),uiRule.getFrequence(),uiRule.getId(),uiRule.getIsActive(),uiRule.getModifiedBy(),uiRule.getModifiedDate(),uiRule.getSevrity(),uiRule.getStatus(),uiRule.getUI_Made_Rule(),uiRule.getVersion());
			return"update Rule Successfully";
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public UIRule getUIRule(Long base_id, String bankCode) {
	try
	{
		String status="Active";
		return uiRuleRepository.findByBaseRuleIdAndBankCodeAndStatus(base_id,bankCode,status);
	}catch (Exception e) {
		// TODO: handle exception
	}
		return null;
	}

	@Override
	public List<Transaction> getTransacrion() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public BaseRules getBaseUIRule() {
		try
		{
			String type="UI";
		return baseRuleRepository.findByType(type);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
