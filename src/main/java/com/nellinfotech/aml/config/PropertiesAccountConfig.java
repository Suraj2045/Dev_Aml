package com.nellinfotech.aml.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Configuration
@PropertySource("classpath:account.properties")
@Service
public class PropertiesAccountConfig {
	@Autowired
    private Environment env;
    
    public String getPropertyValue(String propertyName) {
        return env.getProperty(propertyName);
    }
}
