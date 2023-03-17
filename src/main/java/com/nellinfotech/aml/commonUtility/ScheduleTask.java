package com.nellinfotech.aml.commonUtility;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nellinfotech.aml.controller.EODController;
import com.nellinfotech.aml.controller.FileUploadController;
import com.nellinfotech.aml.controller.RiskProfileController;
import com.nellinfotech.aml.controller.SwiftMessageController;
import com.nellinfotech.aml.entities.EODHistory;
import com.nellinfotech.aml.entities.EODMst;


@Component
public class ScheduleTask {

	@Autowired
    FileUploadController fileUploadController;

	@Autowired
    EODController eodController;
	
	@Autowired
    DateValidator dateValidator;
    
	@Autowired
	SwiftMessageController swiftMessageController;
	
	@Autowired
	Environment env;
	
	@Autowired
	RiskProfileController riskProfileController;
	
	@Scheduled(cron = "${cron.expression}")
	public void scheduleTaskForEOD() {

		try {
			System.out.println("inside scheduler");
			List<EODMst> eodList = eodController.getEODMasterConfiguration();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("bankCode", env.getProperty("bankCode"));
			httpHeaders.set("userId", env.getProperty("userId"));
			
			Date date = new Date();
			
			for(int i=0; i< eodList.size(); i++) {
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    Date date1= format.parse(eodList.get(i).getNextRunDate());  
        
				if(new Integer(date.getDate()).equals(date1.getDate())) {
					EODHistory history1 = new EODHistory();
					history1.setBankCode(env.getProperty("bankCode"));
					history1.setCreatedBy(env.getProperty("userId"));
					history1.setProcessId(eodList.get(i).getProcessId());
					history1.setProcessName(eodList.get(i).getProcessName());
					history1.setStartTime(new Date());
					
					
				    if(eodList.get(i).getFrequency().equals("daily")) {
						Date newDate= format.parse(eodList.get(i).getNextRunDate());  
						newDate = DateUtils.addDays(newDate, 1);
						eodList.get(i).setLastRunDate(eodList.get(i).getNextRunDate());
						eodList.get(i).setNextRunDate(format.format(newDate));
						
						
					    String eodMst = eodController.updateNextRunDate(eodList.get(i));
					}
				    
				    if(eodList.get(i).getFrequency().equals("monthly")) {
						Date newDate= format.parse(eodList.get(i).getNextRunDate());
						if(newDate.getMonth()==12) {
							newDate = DateUtils.setMonths(newDate, 01);
							newDate = DateUtils.addYears(newDate, 1);
						}else {
							newDate = DateUtils.addMonths(newDate, 1);
						}
						eodList.get(i).setLastRunDate(eodList.get(i).getNextRunDate());
						eodList.get(i).setNextRunDate(format.format(newDate));
						
						
					    String eodMst = eodController.updateNextRunDate(eodList.get(i));
					}
				    
					if(eodList.get(i).getProcessId().equals("1")) {
						fileUploadController.uploadCustomerData(httpHeaders);
						fileUploadController.uploadAccountExcel(httpHeaders);
						fileUploadController.uploadTransactionData(httpHeaders);
					}
					
					if(eodList.get(i).getProcessId().equals("5")) {
						riskProfileController.updateRiskProfile(env.getProperty("bankCode"));
					}
					
					if(eodList.get(i).getProcessId().equals("3")) {
						swiftMessageController.uploadOFACcomment(httpHeaders);
						swiftMessageController.uploadOFACAddress(httpHeaders);
						swiftMessageController.uploadOFACAltAddress(httpHeaders);
						swiftMessageController.uploadOFACSdn(httpHeaders);
					}
				
					
					history1.setEndTime(new Date());
					EODHistory history = eodController.saveEODHistory(history1);
				}

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
