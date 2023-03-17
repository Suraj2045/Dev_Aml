package com.nellinfotech.aml.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.BanMerchantMst;
import com.nellinfotech.aml.entities.DeDupConfig;
import com.nellinfotech.aml.entities.Monitor;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.MonitorRepository;
import com.nellinfotech.aml.service.MonitorService;

@CrossOrigin(origins = "*")
@RestController
public class MonitorController {

	  Logger logger = LoggerFactory.getLogger(MonitorController.class);
	  
	  @Autowired
	  MonitorService monitorService;
	  
	  @Autowired
	  MonitorRepository monitorRepository;
		
	  /**
	     * @author Ravi
	     * @param travellog
	     * @return
	     */
	    @PostMapping("/saveMonitoring")
	    public Monitor saveDeDupConfig(@RequestHeader HttpHeaders httpHeaders, @RequestBody Monitor monitor) {
	        try {
	            Header header = HeaderValidator.headerConversion(httpHeaders);
	            return monitorService.saveMonitoring(header, monitor);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	    }
	    
	    /**
	     * @author Ravi
	     * @param httpHeaders
	     * @param bankCode
	     * @return
	     */
	    @GetMapping("/getMonitoring")
	    public List<Monitor> getDeDupByBankCode(@RequestHeader HttpHeaders httpHeaders, @RequestParam String bankCode) {
	        try {
	            Header header = HeaderValidator.headerConversion(httpHeaders);
	            return monitorService.getMonitoring(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	    }
	    
	    /**
	     * @author Ravi
	     * @param httpHeaders
	     * @param branchMst
	     * @return
	     */
	    @PostMapping("/updateMonitoring")
	    public Monitor updateMonitoring(@RequestHeader HttpHeaders httpHeaders, @RequestBody Monitor monitor) {
	        try {
	            Header header = HeaderValidator.headerConversion(httpHeaders);
	            return monitorService.updateMonitoring(header, monitor);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	    }
	    
	    //deleteMonitoring
	    @PostMapping("/deleteMonitoring")
	    public String deleteMonitoring(@RequestBody BanMerchantMst banMaterialMst) {
	    	monitorRepository.deleteById(banMaterialMst.getId());
	        return "monitoring deleted";
	        
	    }

	    /*
	     * @author Suraj
	     * @param fromDate
	     * @param toDate
	     * @return
	     */
	    @PostMapping("/searchMonitoring")
	    public List<Monitor> searchMonitoring(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
	    		@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate)
	    {
	    	return monitorRepository.getAllBetweenDates(fromDate, toDate);    
	    }
}
