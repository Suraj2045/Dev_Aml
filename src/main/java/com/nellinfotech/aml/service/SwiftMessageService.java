package com.nellinfotech.aml.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.nellinfotech.aml.entities.SdnDetails;

public interface SwiftMessageService {
    
    ResponseEntity<Map<String, Object>> uploadSwiftFile(HttpHeaders httpHeaders);

	ResponseEntity<Map<String, Object>> uploadOFACcomment(HttpHeaders httpHeaders);

	ResponseEntity<Map<String, Object>> uploadOFACAddress(HttpHeaders httpHeaders);

	ResponseEntity<Map<String, Object>> uploadOFACAltAddress(HttpHeaders httpHeaders);

	ResponseEntity<Map<String, Object>> uploadOFACSdn(HttpHeaders httpHeaders);

	ResponseEntity<Map<String, Object>> ofacSearchList(HttpHeaders httpHeaders, SdnDetails sdn);

	List<SdnDetails> findByFirstName(String string);

	
	ResponseEntity<Map<String, Object>> customerReverseScan();


    
}
