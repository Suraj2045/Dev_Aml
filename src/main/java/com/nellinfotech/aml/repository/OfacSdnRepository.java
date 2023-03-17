package com.nellinfotech.aml.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.nellinfotech.aml.entities.SdnDetails;

public interface OfacSdnRepository extends SolrCrudRepository<SdnDetails, Integer> {
  

	
		ArrayList<SdnDetails> findByFirstNameIgnoreCaseContainingOrCountryIgnoreCaseContainingOrCityIgnoreCaseContainingOrStateIgnoreCaseContainingOrTranTypeIgnoreCaseContaining(
			String firstName, String country, String city, String state, String address, String tranType);

		List<SdnDetails> findByFirstName(String string);

		ArrayList<SdnDetails> findByFirstNameAndAddress(String nameAddress1, String nameAddress2);


		

		

		
    
}
