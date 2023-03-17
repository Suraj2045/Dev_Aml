package com.nellinfotech.aml.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.nellinfotech.aml.entities.CustomerSolr;

public interface CustomerSolarRepository extends SolrCrudRepository<CustomerSolr, Integer> {
    
}
