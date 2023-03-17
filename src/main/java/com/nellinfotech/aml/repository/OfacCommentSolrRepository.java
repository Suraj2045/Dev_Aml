package com.nellinfotech.aml.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.nellinfotech.aml.entities.OfacComment;

public interface OfacCommentSolrRepository extends SolrCrudRepository<OfacComment, Integer> {
    
}
