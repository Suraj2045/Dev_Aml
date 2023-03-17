package com.nellinfotech.aml.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.nellinfotech.aml.entities.SwiftMsg;

public interface SwiftRepository extends SolrCrudRepository<SwiftMsg, Integer> {

	
    
}
