package com.nellinfotech.aml.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "BRANCH_MASTER")
public class BranchMst extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2588441332070288443L;
    private String branchCode = "";
    private String branchType = ""; //HO, RO, BO
    private String branchName = "";
    private String branchAddress = "";
    private String branchCity, branchState, branchCountry, postalCode;
    private String tele1, tele2, mobile, fax, emailId;
    private String branchManager = "";
    
    public String getBranchCode() {
        return branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    
    public String getBranchType() {
        return branchType;
    }
    
    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }
    
    public String getBranchName() {
        return branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    public String getBranchAddress() {
        return branchAddress;
    }
    
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
    
    public String getBranchCity() {
        return branchCity;
    }
    
    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }
    
    public String getBranchState() {
        return branchState;
    }
    
    public void setBranchState(String branchState) {
        this.branchState = branchState;
    }
    
    public String getBranchCountry() {
        return branchCountry;
    }
    
    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getTele1() {
        return tele1;
    }
    
    public void setTele1(String tele1) {
        this.tele1 = tele1;
    }
    
    public String getTele2() {
        return tele2;
    }
    
    public void setTele2(String tele2) {
        this.tele2 = tele2;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getFax() {
        return fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public String getBranchManager() {
        return branchManager;
    }
    
    public void setBranchManager(String branchManager) {
        this.branchManager = branchManager;
    }
    
    @Override
    public String toString() {
        return "BranchMst [branchCode=" + branchCode + ", branchType=" + branchType + ", branchName=" + branchName
                + ", branchAddress=" + branchAddress + ", branchCity=" + branchCity + ", branchState=" + branchState
                + ", branchCountry=" + branchCountry + ", postalCode=" + postalCode + ", tele1=" + tele1 + ", tele2="
                + tele2 + ", mobile=" + mobile + ", fax=" + fax + ", emailId=" + emailId + ", branchManager="
                + branchManager + "]";
    }
    
}
