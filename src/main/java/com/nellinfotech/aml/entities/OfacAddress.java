package com.nellinfotech.aml.entities;

import javax.persistence.Id;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SolrDocument(collection = "ofac_address")

public class OfacAddress {
    @Id
    @Field
    private Integer id;
    @Field
    private Integer sdnUid;
    @Field
    private String landmark;
    @Field
    private String city;
    @Field
    private String country;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getSdnUid() {
        return sdnUid;
    }
    
    public void setSdnUid(Integer sdnUid) {
        this.sdnUid = sdnUid;
    }
    
    public String getLandmark() {
        return landmark;
    }
    
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
}
