package com.nellinfotech.aml.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.controller.RiskProfileController;
import com.nellinfotech.aml.entities.EODHistory;
import com.nellinfotech.aml.entities.EODMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.EODHistoryRepository;
import com.nellinfotech.aml.repository.EODRepository;
import com.nellinfotech.aml.service.EODService;
import com.nellinfotech.aml.service.FileUploadService;
import com.nellinfotech.aml.service.SwiftMessageService;

@Service
public class EODServiceImpl implements EODService {

	@Autowired
	EODRepository eodRepository;
	
	@Autowired
    DateValidator dateValidator;
	
	@Autowired
	EODHistoryRepository eodHistoryRepository;
	
	@Autowired
	Environment env;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired
    SwiftMessageService swiftMessageService;
	
	@Autowired
	RiskProfileController riskProfileController;
	
	@Override
	public List<EODMst> getEODMasterConfiguration() {
		return eodRepository.findAll();
	}
	

//	@Override
//	public String updateEODConfiguration( List<EODMst> eodList) {
//		try {
//			List<EODMst> eodList1 = new ArrayList<EODMst>();
//			for(int i=0; i<eodList.size(); i++) {
//				EODMst eodMst = eodRepository.findById(eodList.get(i).getId()).orElse(null);
//				eodMst.setFrequency(eodList.get(i).getFrequency());
//				eodMst.setNextRunDate(eodList.get(i).getNextRunDate());
//				eodMst.setUpload(eodList.get(i).getUpload());
////				eodList1.add(eodMst);
//		        eodRepository.saveAndFlush(eodMst);
//
//			}
//
//            
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//        return null;
//	}
	
	@Override
	@Transactional
	public int updateEODConfiguration( List<EODMst> eodList) {
	try {
		//List<EODMst> eodList1 = new ArrayList<EODMst>();
		for(int i=0; i<eodList.size();i++) {
		//EODMst eodMst = eodRepository.findById(eodList.get(i).getId()).orElse(null);
		long id = eodList.get(i).getId();
		String frequency =  eodList.get(i).getFrequency();
		String nextRunDate = eodList.get(i).getNextRunDate();
		String upload= eodList.get(i).getUpload();
		/*eodMst.setFrequency(eodList.get(i).getFrequency());
		eodMst.setNextRunDate(eodList.get(i).getNextRunDate());
		eodMst.setUpload(eodList.get(i).getUpload());*/
		// dList1.add(eodMst);
		eodRepository.saveEodList(id, frequency, nextRunDate, upload );
	}
	        }
	catch (Exception e) {
	        e.printStackTrace();
	        }
	return 1;
	}


	@Override
	public String updateNextRunDate(EODMst eodMst) {
        eodRepository.save(eodMst);
        return "date updated";
	}


	@Override
	public EODHistory saveEODHistory(EODHistory history1) {
	      return eodHistoryRepository.save(history1);
	}


	@Override
	public void runEODConfiguration(List<EODMst> eodList) {
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("bankCode", env.getProperty("bankCode"));
			httpHeaders.set("userId", env.getProperty("userId"));
			
			Date date = new Date();
			
			for(int i=0; i< eodList.size(); i++) {
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    Date date1= format.parse(eodList.get(i).getNextRunDate());  
        
//				if(new Integer(date.getDate()).equals(date1.getDate())) {
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
						
						
					    String eodMst = updateNextRunDate(eodList.get(i));
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
						
						
					    String eodMst = updateNextRunDate(eodList.get(i));
					}
				    
					if(eodList.get(i).getProcessId().equals("1")) {
						fileUploadService.uploadCustomerData(httpHeaders);
						fileUploadService.uploadAccountExcel(httpHeaders);
						fileUploadService.uploadTransactionData(httpHeaders);
					}
					
//					if(eodList.get(i).getProcessId().equals("5")) {
//						riskProfileController.updateRiskProfile(env.getProperty("bankCode"));
//					}
//					
//					if(eodList.get(i).getProcessId().equals("3")) {
//						swiftMessageService.uploadOFACcomment(httpHeaders);
//						swiftMessageService.uploadOFACAddress(httpHeaders);
//						swiftMessageService.uploadOFACAltAddress(httpHeaders);
//						swiftMessageService.uploadOFACSdn(httpHeaders);
//					}
				
					
					history1.setEndTime(new Date());
					EODHistory history = saveEODHistory(history1);
//				}

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
