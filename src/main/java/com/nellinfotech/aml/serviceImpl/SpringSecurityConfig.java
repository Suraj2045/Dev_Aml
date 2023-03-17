package com.nellinfotech.aml.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityConfig {
    @Autowired
    private Environment env;
    
    public String getPropertyValue(String propertyName) {
        return env.getProperty(propertyName);
    }
}
