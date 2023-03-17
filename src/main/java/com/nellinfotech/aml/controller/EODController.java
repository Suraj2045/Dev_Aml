package com.nellinfotech.aml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.EODHistory;
import com.nellinfotech.aml.entities.EODMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.EODService;

/**
 * @author Ravi
 */

@CrossOrigin(origins = "*")
@RestController
public class EODController {

//    Logger logger = LoggerFactory.getLogger(EODController.class);

//    @Autowired
//    EODRepository eodRepository;
//    
    @Autowired
     EODService eodService;
    
//    @PostMapping("/updateEODConfiguration")
//    public String updateEODConfiguration(@RequestBody List<EODMst> eodList) {
//    		//changes
//        Header header = HeaderValidator.headerConversion(httpHeaders);
//        eodService.updateEODConfiguration(eodList);
//        return "EOD updated ";
//    }
    @PostMapping("/updateEODConfiguration")
    public String updateEODConfiguration(@RequestBody List<EODMst> eodList) {
   
//        Header header = HeaderValidator.headerConversion(httpHeaders);
        int i = eodService.updateEODConfiguration(eodList);
        if(i == 1){
        	return "EOD updated ";
        }
        else {
        	return "EOD update failed ";
        	}
    }
    
    @GetMapping("/getEODMasterConfiguration")
    public List<EODMst> getEODMasterConfiguration() {
        try {
            return eodService.getEODMasterConfiguration();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @PostMapping("/updateNextRunDate")
    public String updateNextRunDate(@RequestBody EODMst eodMst) {
    		
        eodService.updateNextRunDate(eodMst);
        return "EOD next run date updated ";
    }

    @PostMapping("/saveEODHistory")
	public EODHistory saveEODHistory(@RequestBody EODHistory history1) {
		return eodService.saveEODHistory(history1);
	        
	}
    
    @PostMapping("/runEODConfiguration")
    public String runEODConfiguration(@RequestBody List<EODMst> eodList) {
    		
//        Header header = HeaderValidator.headerConversion(httpHeaders);
        eodService.runEODConfiguration(eodList);
        return "EOD run successfully";
    }

}
