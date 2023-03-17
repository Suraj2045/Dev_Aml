package com.nellinfotech.aml.service;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    ResponseEntity<Map<String, Object>> uploadAccountExcel(HttpHeaders httpHeaders);
    
    ResponseEntity<Map<String, Object>> uploadCustomerData(HttpHeaders httpHeaders);
    
    ResponseEntity<Map<String, Object>> uploadTransactionData(HttpHeaders httpHeaders);
    
    ResponseEntity<Resource> getFailRecordExcel();
    
}
