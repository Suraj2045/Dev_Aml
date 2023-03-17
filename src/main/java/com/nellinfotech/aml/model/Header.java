package com.nellinfotech.aml.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {
    @JsonProperty("bankcode")
    private String bankCode = "";
    
    @JsonProperty("branchcode")
    private String branchCode = "";
    
    @JsonProperty("currentdate")
    private String currentDate = "";
    
    @JsonProperty("defaultlang")
    private String defaultLang = "";
    
    @JsonProperty("currancy")
    private String currancy = "";
    
    @JsonProperty("userid")
    private String userId = "";
    
    public String getBankCode() {
        return bankCode;
    }
    
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    
    public String getBranchCode() {
        return branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    
    public String getCurrentDate() {
        return currentDate;
    }
    
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
    
    public String getDefaultLang() {
        return defaultLang;
    }
    
    public void setDefaultLang(String defaultLang) {
        this.defaultLang = defaultLang;
    }
    
    public String getCurrancy() {
        return currancy;
    }
    
    public void setCurrancy(String currancy) {
        this.currancy = currancy;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        return "Header [bankCode=" + bankCode + ", branchCode=" + branchCode + ", currentDate=" + currentDate
                + ", defaultLang=" + defaultLang + ", currancy=" + currancy + ", userId=" + userId + "]";
    }
    
}
