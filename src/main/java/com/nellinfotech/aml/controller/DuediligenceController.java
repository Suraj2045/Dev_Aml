package com.nellinfotech.aml.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;

import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.DuediligenceService;
import com.nellinfotech.aml.service.HolidayService;
import com.nellinfotech.aml.serviceImpl.DuediligenceServiceImpl;

/**
 * @author PARESH K
 */
@CrossOrigin(origins = "*")
@RestController
public class DuediligenceController {
    
    @Autowired
    private DuediligenceService duediligenceService;
    
    @Autowired
    private DuediligenceServiceImpl duediligenceServiceImpl;
    
    Logger logger = LoggerFactory.getLogger(DuediligenceController.class);
    
    /**
     * @author PARESH K
     * @param duediligence_mst
     *            return
     */
    @PostMapping("/saveDueDiligenceMst")
    public DueDiligenceMst saveDuediligenceServiceMaster(@RequestHeader HttpHeaders httpHeaders, @RequestBody DueDiligenceMst dueDiligenceMst) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return duediligenceService.saveDueDiligenceMaster(header, dueDiligenceMst);
        } catch (Exception e) {
            logger.error("Duediligencecontroller  - saveDuediligence" + e);
        }
        return null;
    }
    
    /**
     * @author PARESH K
     * @param dueDiligenceMst
     *            return
     */
    @PostMapping("/getDueDiligencelist")
    public List<DueDiligenceMst> getDueDiligencelist(@RequestBody DueDiligenceMst dueDiligenceMst) {
        try {
            
            return duediligenceService.getDueDiligencelist(dueDiligenceMst.getBankCode());
        } catch (Exception e) {
            logger.error("Duediligencecontroller  - getDuediligence" + e);
        }
        return null;
    }
    
    /**
     * @author PARESH K
     * @param dueDiligenceMst
     *            return
     */
    @PostMapping("/updateDueDiligenceMst")
    public DueDiligenceMst updateDueDiligenceMaster(@RequestHeader HttpHeaders httpHeaders, @RequestBody DueDiligenceMst dueDiligenceMst) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return duediligenceService.updateDueDiligenceMaster(header, dueDiligenceMst);
        } catch (Exception e) {
            logger.error("Duediligencecontroller  - updateDuediligence" + e);
        }
        return null;
    }
    
    /**
     * @author PARESH K
     * @param dueDiligenceMst
     *            return
     */
    @PostMapping("/deleteDueDiligenceMst")
    public String deleteDueDiligenceMst(@RequestBody DueDiligenceMst dueDiligenceMst) {
        try {
            
            return duediligenceService.deleteDueDiligenceMaster(dueDiligenceMst.getId());
        } catch (Exception e) {
            logger.error("Duediligencecontroller  - deletehDuediligence" + e);
        }
        return null;
    }
    
    /**
     * @author PARESH K
     * @param dueDiligenceMst
     *            return
     */
   
    
    @PostMapping("/runDueDiligenceJob")
    public ResponseEntity<Resource> runDueDiligenceJob(@RequestParam String bankCode) {
        try {
            return duediligenceService.runDueDiligenceJob(bankCode);
        } catch (Exception e) {
            
        }
        return null;
        
    }
}
