package com.nellinfotech.aml.entities;

import javax.persistence.Id;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Score;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SolrDocument(collection = "ofac_sdnDetails")
public class SdnDetails {
    
    @Id
    @Field
    private Integer id;
    @Field
    private String firstName;
    @Field
    private String tranType;
    @Field
    private String country;
    @Field
    private String Profesion;
    @Field
    private String city;
    @Field
    private String state;
    @Field
    private String address;
    @Field
    private String col4;
    @Field
    private String clo5;
    @Field
    private String col6;
    @Field
    private String col7;
   
  

	@Score
    private Float score;
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getTranType() {
        return tranType;
    }
    
    public void setTranType(String tranType) {
        this.tranType = tranType;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getProfesion() {
        return Profesion;
    }
    
    public void setProfesion(String profesion) {
        Profesion = profesion;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCol4() {
        return col4;
    }
    
    public void setCol4(String col4) {
        this.col4 = col4;
    }
    
    public String getClo5() {
        return clo5;
    }
    
    public void setClo5(String clo5) {
        this.clo5 = clo5;
    }
    
    public String getCol6() {
        return col6;
    }
    
    public void setCol6(String col6) {
        this.col6 = col6;
    }
    
    public String getCol7() {
        return col7;
    }
    
    public void setCol7(String col7) {
        this.col7 = col7;
    }
    
    public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "SdnDetails [id=" + id + ", firstName=" + firstName + ", tranType=" + tranType + ", country=" + country
				+ ", Profesion=" + Profesion + ", city=" + city + ", state=" + state + ", address=" + address
				+ ", col4=" + col4 + ", clo5=" + clo5 + ", col6=" + col6 + ", col7=" + col7 + ", score=" + score + "]";
	}

	
	
	

}
