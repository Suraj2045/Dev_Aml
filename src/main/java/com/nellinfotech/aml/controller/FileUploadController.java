package com.nellinfotech.aml.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nellinfotech.aml.service.FileUploadService;

@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileupload;
    Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    
    /**
     * @author Tushar
     * @apiNote upload bank customer account data
     */
    @RequestMapping(value = "/uploadAccountData", headers = "Content-Type=multipart/form-data",
            method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadAccountExcel(@RequestHeader HttpHeaders httpHeaders) {
        try { 
        	 logger.info(" inside FileUploadController - uploadAccountData-" );
            return fileupload.uploadAccountExcel(httpHeaders);
        } catch (Exception e) {
            logger.error("FileUploadController"+e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @apiNote upload bank customer data
     */
    @RequestMapping(value = "/uploadCustomerData", headers = "Content-Type=multipart/form-data",
            method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadCustomerData(@RequestHeader HttpHeaders httpHeaders) {
        try {
       	 logger.info(" inside FileUploadController - uploadCustomerData-" );
            return fileupload.uploadCustomerData(httpHeaders);
        } catch (Exception e) {
        	 logger.error("FileUploadController"+e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @apiNote upload bank customer Transaction data
     */
    @RequestMapping(value = "/uploadTransactionData", headers = "Content-Type=multipart/form-data",
            method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadTransactionData(@RequestHeader HttpHeaders httpHeaders) {
        try {
        	logger.info(" inside FileUploadController - uploadTransactionData-" );
            return fileupload.uploadTransactionData(httpHeaders);
        } catch (Exception e) {
        	 logger.error("FileUploadController"+e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @apiNote get fail records
     */
    @GetMapping("/getFailRecords")
    public ResponseEntity<Resource> getFailRecordExcel() {
        try {
        	logger.info(" inside FileUploadController - getFailRecordExcel-" );
            return fileupload.getFailRecordExcel();
        } catch (Exception e) {
        	 logger.error("FileUploadController"+e);
        }
        return null;
        
    }
    
}
