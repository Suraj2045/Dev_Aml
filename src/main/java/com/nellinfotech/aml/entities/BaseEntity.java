package com.nellinfotech.aml.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {
    
    private static final long serialVersionUID = -4051978974447080566L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;
    
    @Column(name = "BANK_CODE", length = 20, nullable = false)
    private String bankCode = "";
    
    @Version
    private Integer version = 0;
    
    @Column
    private Integer isActive = 1; // 0 1 
    
    @Column
    private Integer authStatus = 1; // Entry=0, Authorized=1, Reject=2
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = Calendar.getInstance().getTime();
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(nullable = true, length = 20)
    private String createdBy;
    
    @Column
    private String modifiedBy;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Date getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public Integer getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    
    public String getBankCode() {
        return bankCode;
    }
    
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Integer getAuthStatus() {
        return authStatus;
    }
    
    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }
    
}
