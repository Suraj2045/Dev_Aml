package com.nellinfotech.aml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling
public class AmlApiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AmlApiApplication.class, args);
        
    }
    
}
