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
@SolrDocument(collection = "ofac_Altaddress")
public class AltAddress {
    
    @Id
    @Field
    private Integer id;
    @Field
    private Integer sdnIud;
    @Field
    private String altName;
    @Field
    private String orgName;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getSdnIud() {
        return sdnIud;
    }
    
    public void setSdnIud(Integer sdnIud) {
        this.sdnIud = sdnIud;
    }
    
    public String getAltName() {
        return altName;
    }
    
    public void setAltName(String altName) {
        this.altName = altName;
    }
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
}
