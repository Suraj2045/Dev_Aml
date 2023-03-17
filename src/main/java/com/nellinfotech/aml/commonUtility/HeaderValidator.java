package com.nellinfotech.aml.commonUtility;

import java.util.Map;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nellinfotech.aml.model.Header;

public class HeaderValidator {
    
    public static Header headerConversion(HttpHeaders httpHeaders) {
        try {
            Map<String, String> headerMap = httpHeaders.toSingleValueMap();
            final ObjectMapper mapper = new ObjectMapper();
            Header header = mapper.convertValue(headerMap, Header.class);
            return header;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
}
