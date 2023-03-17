package com.nellinfotech.aml.serviceImpl;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.controller.BankController;
import com.nellinfotech.aml.dto.AlertMonitorGraph;
import com.nellinfotech.aml.dto.AlertPerformance;
import com.nellinfotech.aml.dto.AlertResponse;
import com.nellinfotech.aml.dto.AlertTransaction;
import com.nellinfotech.aml.dto.BranchRisk;
import com.nellinfotech.aml.dto.InvestigatorPerformance;
import com.nellinfotech.aml.dto.SenarioListing;
import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.AlertSubTypeMst;
import com.nellinfotech.aml.entities.AlertTypeMst;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.AlertRepository;
import com.nellinfotech.aml.repository.AlertSubTypeRepository;
import com.nellinfotech.aml.repository.AlertTypeRepository;
import com.nellinfotech.aml.repository.TransactionRepository;
import com.nellinfotech.aml.service.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	AlertRepository alertRepository;

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AlertTypeRepository alertTypeRepository;
	
	@Autowired
	AlertSubTypeRepository alertSubTypeRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);

    
	@Override
	public List<Alert> searchAlert(Header header, Alert alert) {
		try {
    		String bankCode=header.getBankCode();
			return alertRepository.searchAlert(alert.getAlertCode(), alert.getAlertStatus(), alert.getCustomerName(),
					alert.getCustId(),alert.getRiskSeverity(),alert.getAssignedUser(),alert.getLocation(),alert.getAccountNo(),alert.getCreatedDate(),alert.getAlertSubTypeCode(),bankCode);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Alert> getAlertStatastics(String bankCode, String interval) {

		double count = 0.00;
		double closedCount = 0.00;
		double wipCount = 0.00;
		double newCount = 0.00;

		double closedCountPercent;
		double wipCountPercent;
		double newCountPercent;

		List<Alert> alert = new ArrayList<Alert>();
		List<Alert> alertGroup = new ArrayList<Alert>();
		try {
			
			if (interval.equals("1 WEEK")) {
				alert = alertRepository.getOneWeekAlertStatastics(bankCode);
			} else if (interval.equals("1 MONTH")) {
				alert = alertRepository.getOneMonthAlertStatastics(bankCode);
			} else if (interval.equals("1 QUARTER")) {
				alert = alertRepository.getOneQuarterAlertStatastics(bankCode);
			} else if (interval.equals("6 MONTH")) {
				alert = alertRepository.getSixMonthAlertStatastics(bankCode);
			} else if (interval.equals("1 YEAR")) {
				alert = alertRepository.getOneYearAlertStatastics(bankCode);
			} else {
				alert = alertRepository.getAlertStatastics(bankCode);
			}

			double alertSize = alert.size();
			for (int i = 0; i < alert.size(); i++) {

				if (alert.get(i).getAlertStatus().equals("Closed"))

				{
					closedCount = closedCount + 1;

				} else if (alert.get(i).getAlertStatus().equals("WIP")) {
					wipCount = wipCount + 1;
				} else if (alert.get(i).getAlertStatus().equals("New")) {
					newCount = newCount + 1;
				} else {
					count = count + 1;
				}

			}

			alertGroup=alertRepository.getAlertStatusbyGroup(bankCode);
			
			for (int j = 0; j < alertGroup.size(); j++) {
				if (alertGroup.get(j).getAlertStatus().equals("Closed")) {
					closedCountPercent = closedCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(closedCountPercent);

				}
				if (alertGroup.get(j).getAlertStatus().equals("WIP")) {
					wipCountPercent = wipCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(wipCountPercent);
				}
				if (alertGroup.get(j).getAlertStatus().equals("New")) {
					newCountPercent = newCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(newCountPercent);
				}
			}

			return alertGroup;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Alert> getAlertClassification(String bankCode, String interval) {
		double count = 0.00;
		double vrvCount = 0.00;
		double sdnCount = 0.00;
		double swiftCount = 0.00;
		double deDupCount = 0.00;
		double manualCount = 0.00;

		double vrvCountPercent;
		double sdnCountPercent;
		double swiftCountPercent;
		double deDupCountPercent;
		double manualCountPercent;

		List<Alert> alert = new ArrayList<Alert>();
		List<Alert> alertGroup = new ArrayList<Alert>();
		
		try {

			if (interval.equals("1 WEEK")) {
				alert = alertRepository.getOneWeekAlertStatastics(bankCode);
			} else if (interval.equals("1 MONTH")) {
				alert = alertRepository.getOneMonthAlertStatastics(bankCode);
			} else if (interval.equals("1 QUARTER")) {
				alert = alertRepository.getOneQuarterAlertStatastics(bankCode);
			} else if (interval.equals("6 MONTH")) {
				alert = alertRepository.getSixMonthAlertStatastics(bankCode);
			} else if (interval.equals("1 YEAR")) {
				alert = alertRepository.getOneYearAlertStatastics(bankCode);
			} else {
				//alert = alertRepository.getAlertStatastics(bankCode);
			}

			double alertSize = alert.size();
			for (int i = 0; i < alert.size(); i++) {

				if (alert.get(i).getAlertCode().equals("VRV"))

				{
					vrvCount = vrvCount + 1;

				} else if (alert.get(i).getAlertCode().equals("SDN")) {
					sdnCount = sdnCount + 1;
				} else if (alert.get(i).getAlertCode().equals("SWIFT")) {
					swiftCount = swiftCount + 1;
				} else if (alert.get(i).getAlertCode().equals("De-dup")) {
					deDupCount = swiftCount + 1;
				} else if (alert.get(i).getAlertCode().equals("Manual")) {
					manualCount = manualCount + 1;
				} else {
					count = count + 1;
				}

			}

			alertGroup=alertRepository.getAlertNamebyGroup(bankCode);
			
			for (int j = 0; j < alertGroup.size(); j++) {
				if (alertGroup.get(j).getAlertCode().equals("VRV")) {
					vrvCountPercent = vrvCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(vrvCountPercent);

				}
				if (alertGroup.get(j).getAlertCode().equals("SDN")) {
					sdnCountPercent = sdnCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(sdnCountPercent);
				}
				if (alertGroup.get(j).getAlertCode().equals("SWIFT")) {
					swiftCountPercent = swiftCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(swiftCountPercent);
				}
					if (alertGroup.get(j).getAlertCode().equals("De-Dup")) {

					}
				if (alertGroup.get(j).getAlertCode().equals("Manual")) {
					manualCountPercent = manualCount * 100 / alertSize;
					alertGroup.get(j).setAlertPercent(manualCountPercent);
				}
			}

			return alertGroup;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Alert> getAlertStatus(String bankCode, String toDate, String fromDate, String alertType,
			String alertStatus) {
		try {
			
				return alertRepository.getAlertStatus(bankCode, toDate, fromDate, alertType,alertStatus);
			
				
			}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AlertTransaction> getAlertViolationStatistics(String bankCode, String toDate, String fromDate) {
		try {
			List<AlertTransaction> alertTransaction1 = new ArrayList<AlertTransaction>();
              List<Alert> alert =  alertRepository.getAlertViolationStatistics(bankCode, toDate, fromDate);
             
              List<Transaction> transaction = transactionRepository.getTransactionByAccountNo( toDate, fromDate);
        	  double total = 0.0;
        	  for(int j=0; j<transaction.size(); j++) {
        		  total = total+transaction.get(j).getAcBalAmt().doubleValue();
        	  }
              for(int i=0; i<alert.size();i++) {
            	  AlertTransaction alertTransaction = new AlertTransaction();
            	  List<Alert> alert1 = alertRepository.getAlertByAlertCode(alert.get(i).getAlertCode() ,bankCode ) ;
            	  alertTransaction.setTotalAmount(total);
            	  alertTransaction.setScannedTransaction(transaction.size());
            	  alertTransaction.setAlertId(alert.get(i).getAlertCode());
            	  alertTransaction.setDescription(alert.get(i).getAlertSubTypeCode());
            	  alertTransaction.setViolation(alert1.size());
            	  alertTransaction1.add(alertTransaction);
             }
              
			return alertTransaction1;
		}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public List<Alert> getChannelWiseAlert(String bankCode, String toDate, String fromDate) {
		try {
			List<Alert> alert = new ArrayList<Alert>();
			
			List<Alert> alert1 =  alertRepository.getChannelWiseAlert(bankCode, toDate, fromDate);
			
			for(int i=0; i<alert1.size(); i++) {
				List<Alert> alert2 = alertRepository.getAlertByChannel(bankCode, toDate, fromDate, alert1.get(i).getChennel(), alert1.get(i).getAlertCode());
				alert1.get(i).setCustomerNo(String.valueOf(alert2.size()));
				alert.add(alert1.get(i));
			}
			return alert;
		}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public List<AlertTypeMst> getAlertType(String bankCode) {
		try {
			return alertTypeRepository.getAlertType(bankCode);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<AlertSubTypeMst> getAlertSubType(String bankCode) {
		try {
			return alertSubTypeRepository.getAlertSubType(bankCode);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public String assignAlert(String bankCode, String assignUser, Long alertId, String assignUserId) {
		try {
			Integer alert = alertRepository.assignAlert(bankCode, assignUser, alertId, assignUserId);
			 return ResponseStatus.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String rejectAlert(String bankCode, String remark, Long alertId) {
		try {
			Integer alert = alertRepository.rejectAlert(bankCode, remark, alertId);
			 return ResponseStatus.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AlertResponse> getAlert(String bankCode, Long alertId) {
		try {
			List<AlertResponse> alertRes = new ArrayList<AlertResponse>();
			Alert alert = alertRepository.getAlert(bankCode, alertId);
			AlertResponse alertResponse = new AlertResponse();
			AlertResponse alertResponse1 = new AlertResponse();
			AlertResponse alertResponse2 = new AlertResponse();
			AlertResponse alertResponse3 = new AlertResponse();
			AlertResponse alertResponse4 = new AlertResponse();
			AlertResponse alertResponse5 = new AlertResponse();
			AlertResponse alertResponse6 = new AlertResponse();
			AlertResponse alertResponse7 = new AlertResponse();
			AlertResponse alertResponse8 = new AlertResponse();
			AlertResponse alertResponse9 = new AlertResponse();
			AlertResponse alertResponse10 = new AlertResponse();
			AlertResponse alertResponse11 = new AlertResponse();
			AlertResponse alertResponse12 = new AlertResponse();
			
			alertResponse.setAttribute("Alert Id");
			alertResponse.setValue(String.valueOf(alertId));
			alertRes.add(alertResponse);
			
			alertResponse1.setAttribute("Transaction No");
			if(alert.getTxnNo()!=null) {
			alertResponse1.setValue(alert.getTxnNo());
			}
			alertRes.add(alertResponse1);
			
			alertResponse2.setAttribute("Transaction Date");
			if(alert.getTxnDtTm()!=null) {
			alertResponse2.setValue(alert.getTxnDtTm().toString());
			}
			alertRes.add(alertResponse2);
			
			alertResponse3.setAttribute("Transaction Currency");
			if(alert.getCurrency()!=null) {
			alertResponse3.setValue(alert.getCurrency());
			}
			alertRes.add(alertResponse3);
			
			alertResponse4.setAttribute("Transaction Amount");
			if(alert.getTxnAmount()!=null) {
			alertResponse4.setValue(alert.getTxnAmount().toString());
			}
			alertRes.add(alertResponse4);
			
			alertResponse5.setAttribute("Transaction Type");
			if(alert.getTxnType()!=null) {
			alertResponse5.setValue(alert.getTxnType());
			}
			alertRes.add(alertResponse5);
			
			alertResponse6.setAttribute("Cashflow");
			if(alert.getCashflowType()!=null) {
			alertResponse6.setValue(alert.getCashflowType());
			}
			alertRes.add(alertResponse6);
			
			alertResponse7.setAttribute("Currency");
			if(alert.getCurrency()!=null) {
			alertResponse7.setValue(alert.getCurrency());
			}
			alertRes.add(alertResponse7);
			
			alertResponse8.setAttribute("Account Number");
			if(alert.getAccountNo()!=null) {
			alertResponse8.setValue(alert.getAccountNo());
			}
			alertRes.add(alertResponse8);
			
			alertResponse9.setAttribute("Customer Information");
			if(alert.getCustId()!=null) {
			alertResponse9.setValue(alert.getCustId());
			}
			alertRes.add(alertResponse9);
			
			alertResponse10.setAttribute("FCY Amount");
			if(alert.getCustomerName()!=null) {
			alertResponse10.setValue(alert.getCustomerName());
			}
			alertRes.add(alertResponse10);
			
			alertResponse11.setAttribute("Scenario Violated");
			if(alert.getAlertSubTypeCode()!=null) {
			alertResponse11.setValue(alert.getAlertSubTypeCode());
			}
			alertRes.add(alertResponse11);
			
			alertResponse12.setAttribute("Assigned To");
			if(alert.getAssignedUser()!=null) {
			alertResponse12.setValue(alert.getAssignedUser());
			}
			alertRes.add(alertResponse12);
			
			 return alertRes;
		} catch (Exception e) {
		}
		return null;
	}
	@Override
	public List<Alert> getAlertMonitor(String bankCode, String interval, String type) {
		Map<String,List<Integer>> m=new HashedMap<String, List<Integer>>();
		Integer week=7;
		Integer month=30;
		Integer quarter=3;
		Integer sixmonth=6;
		Integer year=12;
		try
		{
			List<Alert> alert = new ArrayList<Alert>();
			if (interval.equals("1 WEEK")) {
				alert = alertRepository.getOneWeekAlertStatastics(bankCode);
			} else if (interval.equals("1 MONTH")) {
				alert = alertRepository.getOneMonthAlertStatastics(bankCode);
			} else if (interval.equals("1 QUARTER")) {
				alert = alertRepository.getOneQuarterAlertStatastics(bankCode);
			} else if (interval.equals("6 MONTH")) {
				alert = alertRepository.getSixMonthAlertStatastics(bankCode);
			} else if (interval.equals("1 YEAR")) {
				alert = alertRepository.getOneYearAlertStatastics(bankCode);
			} else 
			{
				alert = alertRepository.getAlertStatastics(bankCode);
			}
			Map<LocalDate,List<Integer>>lst=new HashedMap<LocalDate, List<Integer>>();
			for(int i=1;i<=7;i++)
			{
				LocalDate weekBeforeToday = LocalDate.now().minusDays(i);
				int vrv=0;
				int crt=0;
				int srt=0;
				List<Integer>count=new ArrayList<Integer>();
				for (Alert alert2 : alert) {
					LocalDate localDate = alert2.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();								
					///&&(alert2.getCreatedDate().getMonth()+1)==weekBeforeToday.getMonthValue()&&alert2.getCreatedDate().getYear()==weekBeforeToday.getYear()
					if(weekBeforeToday.compareTo(localDate)==0)
					//if(alert2.getCreatedDate().getDate()==weekBeforeToday.getDayOfMonth()&&(alert2.getCreatedDate().getMonth()+1)==weekBeforeToday.getMonthValue())
					{
						  
						if(alert2.getAlertCode().contentEquals("VRV"))
						{
							vrv=vrv+1;
						}
						if(alert2.getAlertCode().contentEquals("CRT"))
						{
							crt=crt+1;
						}
						if(alert2.getAlertCode().contentEquals("SRT"))
						{
							srt=srt+1;
						}			
//						System.out.println(alert2.getCreatedDate().getDate()+"yyyy"+alert2.getCreatedDate().getYear()+"------"+year1);
//						System.out.println(weekBeforeToday.getYear());
//						System.out.println((alert2.getCreatedDate().getMonth()+1)==weekBeforeToday.getMonthValue());
//						System.out.println(alert2.getCreatedDate()+"----------------------====");
					}
								
				}
				count.add(vrv);
				count.add(crt);
				count.add(srt);
				lst.put(weekBeforeToday, count);
				
			}
			Map result = lst.entrySet().stream()
				    .sorted(Map.Entry.comparingByKey()) 			
				    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			System.out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Alert> getAlertAuditByUser(String bankCode,String toDate, String fromDate) {
		try {
			
			return alertRepository.getAlertAuditByUser(bankCode,toDate, fromDate);
		
			
		}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public List<Alert> getActivityAudit(String bankCode, String activity, String toDate, String fromDate) {
	try {
			
			return alertRepository.getActivityAudit(bankCode, activity, toDate, fromDate);
		
			
		}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public List<Alert> getAllAlertStatus(String bankCode) {
      try {	
			return alertRepository.getAllAlertStatus(bankCode);
		}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public List<AlertMonitorGraph> alertMonitorGraph(String bankCode, String category, String searchPeriod) {
		List<AlertMonitorGraph> obj=new ArrayList<AlertMonitorGraph>();
		try
		{
			logger.info("inside alertPerformance"+searchPeriod);
		if(searchPeriod.contentEquals("1 Week"))
		{
		obj=jdbcTemplate.query(" SELECT alertsubtype_code,count(alertsubtype_code) count "+
				"	FROM alert where alert_code=? and bank_code=? and  (DATE_SUB(CURDATE(), INTERVAL 1 WEEK) < created_date) " +
				"	GROUP BY alertsubtype_code ",
				new Object[] { category, bankCode }, new BeanPropertyRowMapper<AlertMonitorGraph>(AlertMonitorGraph.class));
		
		}
		else if(searchPeriod.contentEquals("1 Month"))
		{
			obj=jdbcTemplate.query(" SELECT alertsubtype_code,count(alertsubtype_code) count "+
					"	FROM alert where alert_code=? and bank_code=? and  (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < created_date) " +
					"	GROUP BY alertsubtype_code ",
					new Object[] { category, bankCode }, new BeanPropertyRowMapper<AlertMonitorGraph>(AlertMonitorGraph.class));
			}
		
		else if(searchPeriod.contentEquals("1 Quarter"))
		{
			obj=jdbcTemplate.query(" SELECT alertsubtype_code,count(alertsubtype_code) count "+
					"	FROM alert where alert_code=? and bank_code=? and  (DATE_SUB(CURDATE(), INTERVAL 1 QUARTER) < created_date) " +
					"	GROUP BY alertsubtype_code ",
					new Object[] { category, bankCode }, new BeanPropertyRowMapper<AlertMonitorGraph>(AlertMonitorGraph.class));
			}
		else if(searchPeriod.contentEquals("6 Month"))
		{
			obj=jdbcTemplate.query(" SELECT alertsubtype_code,count(alertsubtype_code) count "+
					"	FROM alert where alert_code=? and bank_code=? and  (DATE_SUB(CURDATE(), INTERVAL 6 MONTH) < created_date) " +
					"	GROUP BY alertsubtype_code ",
					new Object[] { category, bankCode }, new BeanPropertyRowMapper<AlertMonitorGraph>(AlertMonitorGraph.class));
			}
		else if(searchPeriod.contentEquals("1 Year"))
		{
			obj=jdbcTemplate.query(" SELECT alertsubtype_code,count(alertsubtype_code) count "+
					"	FROM alert where alert_code=? and bank_code=? and  (DATE_SUB(CURDATE(), INTERVAL 1 YEAR) < created_date) " +
					"	GROUP BY alertsubtype_code ",
					new Object[] { category, bankCode }, new BeanPropertyRowMapper<AlertMonitorGraph>(AlertMonitorGraph.class));
			}
		return obj;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<AlertPerformance> alertPerformance(String bankCode,String searchPeriod) {
		List<AlertPerformance> obj=new ArrayList<AlertPerformance>();
		try
		{
			logger.info("inside alertPerformance"+searchPeriod);
		if(searchPeriod.contentEquals("1 Week"))
		{
		obj=jdbcTemplate.query(" SELECT count(CASE WHEN alert_status='Close' THEN alert_status ELSE NULL END) AS closeCount, " +
				" count(CASE WHEN alert_status='WIP' THEN alert_status ELSE NULL END) AS pendingCount, " +
				" count(alert_status)AS totalAssigned,assigned_userid, assigned_user AS assignedUser   " +
				" FROM alert WHERE  bank_code=? AND  (DATE_SUB(CURDATE(), INTERVAL 1 WEEK) < created_date)  " +
				" GROUP BY assigned_userid ",
				new Object[] {  bankCode }, new BeanPropertyRowMapper<AlertPerformance>(AlertPerformance.class));
		
		}
		else if(searchPeriod.contentEquals("1 Month"))
		{
			obj=jdbcTemplate.query(" SELECT count(CASE WHEN alert_status='Close' THEN alert_status ELSE NULL END) AS closeCount, " +
					" count(CASE WHEN alert_status='WIP' THEN alert_status ELSE NULL END) AS pendingCount, " +
					" count(alert_status)AS totalAssigned,assigned_userid,assigned_user AS assignedUser  " +
					" FROM alert WHERE  bank_code=? AND  (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < created_date)  " +
					" GROUP BY assigned_userid ",
					new Object[] {  bankCode }, new BeanPropertyRowMapper<AlertPerformance>(AlertPerformance.class));
		}
		
		else if(searchPeriod.contentEquals("1 Quarter"))
		{
			obj=jdbcTemplate.query(" SELECT count(CASE WHEN alert_status='Close' THEN alert_status ELSE NULL END) AS closeCount, " +
					" count(CASE WHEN alert_status='WIP' THEN alert_status ELSE NULL END) AS pendingCount, " +
					" count(alert_status)AS totalAssigned,assigned_userid , assigned_user AS assignedUser " +
					" FROM alert WHERE  bank_code=? AND  (DATE_SUB(CURDATE(), INTERVAL 1 QUARTER) < created_date)  " +
					" GROUP BY assigned_userid ",
					new Object[] {  bankCode }, new BeanPropertyRowMapper<AlertPerformance>(AlertPerformance.class));
		}
		else if(searchPeriod.contentEquals("6 Month"))
		{
			obj=jdbcTemplate.query(" SELECT count(CASE WHEN alert_status='Close' THEN alert_status ELSE NULL END) AS closeCount, " +
					" count(CASE WHEN alert_status='WIP' THEN alert_status ELSE NULL END) AS pendingCount, " +
					" count(alert_status)AS totalAssigned,assigned_userid , assigned_user AS assignedUser " +
					" FROM alert WHERE  bank_code=? AND  (DATE_SUB(CURDATE(), INTERVAL 6 MONTH) < created_date)  " +
					" GROUP BY assigned_userid ",
					new Object[] {  bankCode }, new BeanPropertyRowMapper<AlertPerformance>(AlertPerformance.class));
		}
		else if(searchPeriod.contentEquals("1 Year"))
		{
			obj=jdbcTemplate.query(" SELECT count(CASE WHEN alert_status='Close' THEN alert_status ELSE NULL END) AS closeCount, " +
					" count(CASE WHEN alert_status='WIP' THEN alert_status ELSE NULL END) AS pendingCount, " +
					" count(alert_status)AS totalAssigned,assigned_userid, assigned_user AS assignedUser  " +
					" FROM alert WHERE  bank_code=? AND  (DATE_SUB(CURDATE(), INTERVAL 1 YEAR) < created_date)  " +
					" GROUP BY assigned_userid ",
					new Object[] {  bankCode }, new BeanPropertyRowMapper<AlertPerformance>(AlertPerformance.class));
		}
		return obj;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<InvestigatorPerformance> caseInvestigatorPerformance(String bankCode, String searchPeriod) {
		try
		{
			logger.info("inside caseInvestigatorPerformance"+searchPeriod);
			List<InvestigatorPerformance>investigatorList=new ArrayList<InvestigatorPerformance>();
			List<AlertPerformance> alertperformance=alertPerformance(bankCode, searchPeriod);
			for (AlertPerformance alertPerformance2 : alertperformance) {
				Double performancePercentage=null;
				InvestigatorPerformance investigator=new InvestigatorPerformance();
				Double cCount=new Double(alertPerformance2.getCloseCount());
				Double pCount=new Double(alertPerformance2.getTotalAssigned());
				if(alertPerformance2.getTotalAssigned()>0) {
				performancePercentage=(cCount/pCount)*100;
							}
			
				investigator.setAssigned_userid(alertPerformance2.getAssigned_userid());
				investigator.setPerformancePercent(new Double(String.format("%.2f", performancePercentage)));
				investigator.setAssignedUser(alertPerformance2.getAssignedUser());
				investigatorList.add(investigator);
			}
			
			return investigatorList;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<BranchRisk> branchRiskMonitor(String bankCode, String alertCode, String alertSubTypeCode,String timeDiff) {
		List<BranchRisk> obj=new ArrayList<BranchRisk>();
		String query=" SELECT Alert.branch_code,branch_master.branch_name,count(Alert.alertsubtype_code) alertCount,Alert.txn_amt As fraudAmount,count(CASE WHEN Cases.confirmed_case='Yes' THEN alert_status ELSE NULL END) AS confirmCount " +
				" FROM Alert " +
				" INNER JOIN Cases " +
				" ON Alert.id = Cases.alert_id INNER JOIN branch_master ON branch_master.branch_code=Alert.branch_code where Alert.alert_code=? and Alert.alertsubtype_code=? and Alert.bank_code=? AND  (DATE_SUB(CURDATE(), INTERVAL "+ timeDiff +") < Alert.created_date)  group by Alert.branch_code ";
		try
		{
			logger.info("inside alertPerformance"+alertCode);
		if(alertCode!=null)
		{
		obj=jdbcTemplate.query(query,
				new Object[] { alertCode,alertSubTypeCode, bankCode }, new BeanPropertyRowMapper<BranchRisk>(BranchRisk.class));
		
		}
		return obj;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public  ResponseEntity<Map<String, Object>> riskHeatMap(String bankCode, String alertCode, String timeDiff) {
		ResponseEntity<Map<String, Object>> result=null;
		List<Integer> low=new ArrayList<Integer>();
		List<Integer> medium=new ArrayList<Integer>();
		List<Integer> high=new ArrayList<Integer>();
		try
		{
			Map<String,Object> severityMap =new HashedMap<String, Object>();
			 HttpHeaders headers = new HttpHeaders();
			
			String query=" Select count(id) AS risk_count from alert where alert_code=? and bank_Code=? and risk_severity<50 AND  (DATE_SUB(CURDATE(), INTERVAL "+ timeDiff +") ) group by created_date,risk_severity ";
			String query1=" Select count(id) AS risk_count from alert where alert_code=? and bank_Code=? and risk_severity>=50 AND risk_severity<80 AND  (DATE_SUB(CURDATE(), INTERVAL "+ timeDiff +") ) group by created_date,risk_severity ";
			String query2=" Select count(id) AS risk_count from alert where alert_code=? and bank_Code=? and risk_severity>=80 AND  (DATE_SUB(CURDATE(), INTERVAL "+ timeDiff +") ) group by created_date,risk_severity ";
			try
			{
			  low=jdbcTemplate.queryForList(query,new Object[] { alertCode, bankCode },Integer.class);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			try {
				medium=jdbcTemplate.queryForList(query1,new Object[] { alertCode, bankCode }, Integer.class) ;
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				high=jdbcTemplate.queryForList(query2,new Object[] { alertCode, bankCode },Integer.class);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			severityMap.put("low", low);
			severityMap.put("medium", medium);
			severityMap.put("high", high);
			 result = (new ResponseEntity<Map<String, Object>>(severityMap,headers , HttpStatus.OK));
             return result;
		}catch (Exception e)
		{
			
		}
		return null;
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> senarioSimulation(String bankCode, Date startDate, Date endDate,Long ruleId) {
		ResponseEntity<Map<String, Object>> result=null;
		List<SenarioListing> obj=new ArrayList<SenarioListing>();
		Map<String,Object> listbject =new HashedMap<String, Object>();
		 HttpHeaders headers = new HttpHeaders();
		String query=new String(" select baserules.scenario_description as scenario_description,alert.customer_no as customer_no,alert.customer_name as customer_name,alert.risk_severity as risk_severity from baserules INNER JOIN  alert ON baserules.id=alert.base_rule_id  where  alert.created_date >=? and  alert.created_date <=? and alert.base_Rule_Id=?  and alert.bank_code=? ");
		try
		{ obj=jdbcTemplate.query(query,new Object[] { startDate,endDate , ruleId, bankCode }, new BeanPropertyRowMapper<SenarioListing>(SenarioListing.class));
			listbject.put("records", obj);
			 return result = (new ResponseEntity<Map<String, Object>>(listbject,headers , HttpStatus.OK));	
		}catch (Exception e) {
			
		}
		return null;
	}
	
}
