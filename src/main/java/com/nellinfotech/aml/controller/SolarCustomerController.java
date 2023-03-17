package com.nellinfotech.aml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.CustomerSolr;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.CustomerSolarRepository;

@RestController
public class SolarCustomerController {
    
    @Autowired
    CustomerSolarRepository custSolrRepository;
    @Autowired
    CustomerRepository cutomerRepository;
    
    @PostMapping("/saveCustomerSolr")
    public String saveSolar(@RequestBody List<CustomerSolr> custSolar) {
        try {
            custSolrRepository.saveAll(custSolar);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @GetMapping("/getAll")
    public Iterable<Customer> getCustomerSolar() {
        try {
            return cutomerRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
