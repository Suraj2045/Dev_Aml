package com.nellinfotech.aml.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.dto.AlertStatistics;
import com.nellinfotech.aml.dto.TemplateDTO;
import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.Case;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.CaseRepository;
import com.nellinfotech.aml.service.CaseService;


/**
 * @author TUSHAR
 */

@Service
public class CaseServiceImpl implements CaseService {
    
    @Autowired
    CaseRepository caseRepository;
    
    
    @Autowired
    DateValidator dateValidator;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    
    @Override
    public Case saveCase(Header header, Case amlCase) {
    	long caseNumber;
    	String caseID=null;
    	String pattern = "dd/MM/yyyy";
    	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
//            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
        	 Date currentDate = new Date();
             String date = simpleDateFormat.format(currentDate);
             List<Case> getLastReord=new ArrayList<Case>();
            getLastReord=caseRepository.getLastRecord();
            caseNumber=getLastReord.get(0).getId();
            caseNumber=caseNumber+1;
            
            caseID=date.toString()+"/"+caseNumber;
            
            amlCase.setCaseID(caseID);
            amlCase.setBankCode(header.getBankCode());
            amlCase.setCreatedBy(header.getUserId());
            amlCase.setCreatedDate(currentDate);
            amlCase.setCaseStatus("Created");
            return caseRepository.save(amlCase);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

	@Override
	public List<Case> getCaseList(String bankCode, String toDate, String fromDate, String alertType) {
		try {

		return caseRepository.getCaseList(bankCode, toDate, fromDate, alertType);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Case> getDashboardCaseList(String bankCode, String toDate, String fromDate, String alertType,
			String alertSubCode, String customerRisk) {
		try {

			return caseRepository.getDashboardCaseList(bankCode, toDate, fromDate, alertType, alertSubCode, customerRisk);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Case> getCaseListByCustomerID(String bankCode, String custCode) {
		try {
			return caseRepository.findByCustCode(bankCode, custCode);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public List<Case> getCaseListByCaseID(String bankCode, String caseID) {
		try {
			return caseRepository.findByCaseID(bankCode, caseID);
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public List<Case> getConfirmedFraudList(String bankCode, String toDate, String fromDate, String operator, String amount) {
		try {

			//return caseRepository.getConfirmedFraudList(bankCode, toDate, fromDate, operator, amount);
			return caseRepository.getConfirmedFraudList(bankCode, toDate, fromDate);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Case> searchCase(String bankCode, String toDate, String fromDate, String caseID, String creatorName,
			String customerName, String customerNumber, String status) {
		
		System.out.println("ToDate "+toDate);
		System.out.println("fromDate "+fromDate);
		try {

			return caseRepository.searchCase(bankCode, toDate, fromDate, caseID, creatorName,customerName,customerNumber, status);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Case> updateCaseStatus(Header header,  List<Case> amlCase) {
	     try {
	    	 for(int i =0; i<amlCase.size(); i++) {
	    		  Case existingCase=caseRepository.findById(amlCase.get(i).getId()).orElse(null);
	 	         //  Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	 	           existingCase.setCaseStatus(amlCase.get(i).getCaseStatus());
	 	           existingCase.setModifiedBy(header.getUserId());
	 	          // existingCase.setModifiedDate(currentDate);
	 	             caseRepository.save(existingCase);    
	    	 }
	    	 return amlCase;
	         	                 
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	
	        }
		return null;
	}

	@Override
	public List<Case> updateConfirmFraud(Header header, List<Case> amlCase) {
		try {
			for(int i =0; i<amlCase.size(); i++) {
	           Case existingCase=caseRepository.findById(amlCase.get(i).getId()).orElse(null);
//	           Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	           existingCase.setConfirmedCase(amlCase.get(i).getConfirmedCase());
//	            existingCase.setModifiedDate(currentDate);
	            existingCase.setModifiedBy(header.getUserId());
	             caseRepository.save(existingCase);
			}
			return amlCase;
	       } catch (Exception e) {
	        	e.printStackTrace();
	        	
	        }
		return null;
	}

	@Override
	public List<Case> getCase(String bankCode, String caseID) {
		try {

			return caseRepository.getCase(bankCode, caseID);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

	

	
    
    /**
     * @author TUSHAR
     * @param branchHolidayMap
     *            return
     */
//    @Override
//    public BranchHolidayMap saveBranchHoliday(Header header, BranchHolidayMap branchHolidayMap) {
//        
//        try {
//            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
//            branchHolidayMap.setCreatedBy(header.getUserId());
//            branchHolidayMap.setCreatedDate(currentDate);
//            branchHolidayMap.setBankCode(header.getBankCode());
//            return branchHolidayRpository.save(branchHolidayMap);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
//    
//    /**
//     * @author TUSHAR
//     * @param brCode
//     *            return
//     */
//    @Override
//    public List<BranchHolidayMap> getBranchHollidayList(String brCode) {
//        try {
//            Integer isactive = 1;
//            return branchHolidayRpository.findBybrCode(brCode, isactive);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
//    
//    /**
//     * @author TUSHAR
//     * @param branchCode
//     *            return
//     */
//    @Override
//    public HolidayMst getHolidaylist(String branchCode) {
//        try {
//            return holidayRpository.findByBranchCode(branchCode);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
//    
//    /**
//     * @author TUSHAR
//     * @param holidayMst
//     *            return
//     */
//    @Override
//    public HolidayMst updateHolidayMaster(Header header, HolidayMst holidayMst) {
//        try {
//            HolidayMst existingHolidayMst = holidayRpository.findById(holidayMst.getId()).orElse(null);
//            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
//            existingHolidayMst.setModifiedBy(header.getUserId());
//            existingHolidayMst.setModifiedDate(currentDate);
//            existingHolidayMst.setBranchCode(holidayMst.getBankCode());
//            existingHolidayMst.setHolidayCode(holidayMst.getHolidayCode());
//            existingHolidayMst.setHolidayDesc(holidayMst.getHolidayDesc());
//            existingHolidayMst.setHolidayDate(holidayMst.getHolidayDate());
//            existingHolidayMst.setBranchCode(holidayMst.getBranchCode());
//            existingHolidayMst.setBankCode(holidayMst.getBankCode());
//            return holidayRpository.save(existingHolidayMst);
//            
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
//    
//    /**
//     * @author TUSHAR
//     * @param id
//     *            return
//     */
//    @Override
//    public String deleteHolidayMst(Long id) {
//        try {
//            HolidayMst existingHolidayMst = holidayRpository.findById(id).orElse(null);
//            existingHolidayMst.setIsActive(1);
//            holidayRpository.save(existingHolidayMst);
//            return ResponseStatus.SUCCESS;
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
    
	@Override
	public List<AlertStatistics> alertStatistics(Header header) {
		List<AlertStatistics>lst=new ArrayList<AlertStatistics>();
		List<TemplateDTO> obj=new ArrayList<TemplateDTO>();
		List<TemplateDTO> obj1=new ArrayList<TemplateDTO>();
		List<TemplateDTO> obj2=new ArrayList<TemplateDTO>();
		try
		{	
		obj=jdbcTemplate.query("select alert.alertsubtype_code as name,COUNT(alert.alertsubtype_code) as totalCount from alert where bank_code=?  group by alertsubtype_code ",
				new Object[] { header.getBankCode() }, new BeanPropertyRowMapper<TemplateDTO>(TemplateDTO.class));	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try
		{	
		obj1=jdbcTemplate.query("select alertsubtype_code as name,count(created_date) as totalCount from alert where bank_code=? and CURDATE()=date(created_date) group by alertsubtype_code ",
				new Object[] { header.getBankCode() }, new BeanPropertyRowMapper<TemplateDTO>(TemplateDTO.class));	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try
		{	
		obj2=jdbcTemplate.query("select alertsubtype_code as name,count(created_date)as totalCount from alert where bank_code=? and CURDATE()>created_date group by alertsubtype_code ",
				new Object[] { header.getBankCode() }, new BeanPropertyRowMapper<TemplateDTO>(TemplateDTO.class));	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(obj.size()>0)
		{
			for (TemplateDTO templateDTO : obj) {
				AlertStatistics stat=new AlertStatistics();
				if(obj1.size()>0)
				{
					for (TemplateDTO templateDTO1 : obj1) {
						
						 if(templateDTO.getName().contentEquals(templateDTO1.getName()))
						 {
							 stat.setAlertName(templateDTO.getName());
							 stat.setTotalCount(templateDTO.getTotalCount());
							 stat.setDayCount(templateDTO1.getTotalCount());
							
						 }
					}
				}
				if(obj2.size()>0)
				{
					for (TemplateDTO templateDTO2 : obj2) {
						
						 if(templateDTO.getName().contentEquals(templateDTO2.getName()))
						 {
							 stat.setAlertName(templateDTO.getName());
							 stat.setTotalCount(templateDTO.getTotalCount());
							 stat.setPrevCount(templateDTO2.getTotalCount());
							;
						 }
					}
				}
				lst.add(stat);
			}
		}
		return lst;
	}
}
