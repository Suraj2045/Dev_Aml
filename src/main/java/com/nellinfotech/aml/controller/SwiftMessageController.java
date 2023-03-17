package com.nellinfotech.aml.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.entities.SdnDetails;
import com.nellinfotech.aml.repository.OfacSdnRepository;
import com.nellinfotech.aml.service.SwiftMessageService;

@CrossOrigin(origins = "*")
@RestController
public class SwiftMessageController {
	 @Autowired
	    SwiftMessageService swiftMessageService;
	 @Autowired
	 OfacSdnRepository ofacSdnRepository;
	    
	 Logger logger = LoggerFactory.getLogger(SwiftMessageController.class);
	 /**
	     * @author Tushar
	     * @apiNote upload swift message file cut paste read insert solar
	     */
	    @RequestMapping(value = "/uploadswift", method = RequestMethod.POST)
	    public ResponseEntity<Map<String, Object>> uploadSwiftFile(@RequestHeader HttpHeaders httpHeaders) {
	        try {
	        	logger.info(" inside SwiftMessageController - uploadSwiftFile-");
	            return swiftMessageService.uploadSwiftFile(httpHeaders);
	        } catch (Exception e) {
	        	logger.error("uploadSwiftFile "+e);
	        }
	        return null;
	    }
	    
	    /**
	     * @author Tushar
	     * @apiNote upload sdn ofac  comment csv file
	     */
	    @RequestMapping(value = "/uploadOFACcomment",method = RequestMethod.POST)
	    public ResponseEntity<Map<String, Object>> uploadOFACcomment(@RequestHeader HttpHeaders httpHeaders) {
	        try {
	        	logger.info(" inside SwiftMessageController - uploadOFACcomment-");
	            return swiftMessageService.uploadOFACcomment(httpHeaders);
	        } catch (Exception e) {
	        	logger.error("uploadOFACcomment "+e);
	        }
	        return null;
	    }
	    /**
	     * @author Tushar
	     * @apiNote upload sdn ofac  Address csv file
	     */
	    @RequestMapping(value = "/uploadOFACAddress", method = RequestMethod.POST)
	    public ResponseEntity<Map<String, Object>> uploadOFACAddress(@RequestHeader HttpHeaders httpHeaders) {
	        try {
	        	logger.info(" inside SwiftMessageController - uploadOFACAddress-");
	            return swiftMessageService.uploadOFACAddress(httpHeaders);
	        } catch (Exception e) {
	        	logger.error("uploadOFACAddress "+e);
	        }
	        return null;
	    }
	    /**
	     * @author Tushar
	     * @apiNote upload sdn ofac  Alternate Address csv file
	     */
	    @RequestMapping(value = "/uploadOFACAltAddress", method = RequestMethod.POST)
	    public ResponseEntity<Map<String, Object>> uploadOFACAltAddress(@RequestHeader HttpHeaders httpHeaders) {
	        try {
	        	logger.info(" inside SwiftMessageController - uploadOFACAltAddress-");
	            return swiftMessageService.uploadOFACAltAddress(httpHeaders);
	        } catch (Exception e) {
	        	logger.error("uploadOFACAltAddress "+e);
	        }
	        return null;
	    }
	    
	    /**
	     * @author Tushar
	     * @apiNote upload  ofac  sdn csv file
	     */
	    @RequestMapping(value = "/uploadOFACSdn", method = RequestMethod.POST)
	    public ResponseEntity<Map<String, Object>> uploadOFACSdn(@RequestHeader HttpHeaders httpHeaders) {
	        try {
	        	logger.info(" inside SwiftMessageController - uploadOFACSdn-");
	            return swiftMessageService.uploadOFACSdn(httpHeaders);
	        } catch (Exception e) {
	        	logger.error("uploadOFACSdn "+e);
	        }
	        return null;
	    }
	    
	    /**
	     * @author Tushar
	     * @apiNote upload  ofac search 
	     */
	    @PostMapping("/ofacSearchList")
	    public ResponseEntity<Map<String, Object>> ofacSearchList(@RequestHeader HttpHeaders httpHeaders,
	            @RequestBody SdnDetails sdn) {
	        try {
	        	
	        	logger.info(" inside SwiftMessageController - ofacSearchList-");
	            return swiftMessageService.ofacSearchList(httpHeaders, sdn);
	        } catch (Exception e) {
	        	logger.error("ofacSearchList "+e);
	        }
	        return null;
	    }
	    
	    /**
	     * @author Tushar
	     * @apiNote customer Reverse Scan
	     */
	    @PostMapping("/customerReverseScan")
	    public ResponseEntity<Map<String, Object>> customerReverseScan(@RequestHeader HttpHeaders httpHeaders) {
	        try {
	        	logger.info(" inside SwiftMessageController - customerReverseScan-");
	        	List<SdnDetails>sdn=new ArrayList<SdnDetails>();
	        	
	            return swiftMessageService.customerReverseScan();
	        } catch (Exception e) {
	        	logger.error("ofacSearchList "+e);
	        }
	        return null;
	    }
	    
	  
}
