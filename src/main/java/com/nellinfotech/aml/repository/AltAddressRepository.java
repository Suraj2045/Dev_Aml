package com.nellinfotech.aml.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.nellinfotech.aml.entities.AltAddress;

public interface AltAddressRepository extends SolrCrudRepository<AltAddress, Integer> {

	AltAddress findBySdnIud(Integer id);

	
    
}
