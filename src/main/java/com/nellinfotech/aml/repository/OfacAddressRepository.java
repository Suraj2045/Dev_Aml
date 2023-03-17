package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.nellinfotech.aml.entities.OfacAddress;

public interface OfacAddressRepository extends SolrCrudRepository<OfacAddress, Integer> {
	@Query("landmark:?0 OR city:?0 OR country:?0")
	List<OfacAddress> findAddress(String nameAddress2);
    
}
