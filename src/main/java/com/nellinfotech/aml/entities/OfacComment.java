package com.nellinfotech.aml.entities;

import javax.persistence.Id;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(collection = "ofac_commentdata")
public class OfacComment {
    @Id
    @Field
    private int id;
    @Field
    private Integer commentNumber;
    @Field
    private String commentDetails;
    
    public Integer getCommentNumber() {
        return commentNumber;
    }
    
    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }
    
    public String getCommentDetails() {
        return commentDetails;
    }
    
    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
}
