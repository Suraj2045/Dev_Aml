package com.nellinfotech.aml.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "AccountFail")
public class AccountFail extends BaseEntity {
    
    private static final long serialVersionUID = 3370969462593463465L;
    
    @Column(name = "CUST_CODE", nullable = true, length = 25)
    private String custCode = "";
    
    @Column(name = "ACCT_NO", nullable = true, length = 25)
    private String acctNo = "";
    @Column(name = "UPLOAD_COUNT", nullable = true)
    
    private Integer uploadCount;
    @Column(name = "UPLOAD_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDateTime = Calendar.getInstance().getTime();
    
    @Column(name = "ACCT_TYPE", nullable = true, length = 25)
    private String acctType = ""; // From Lookup(CUSTOMER_TYPE)-USER e.g.: SB, CA, FD, RD, LP, LH
    
    @Column(name = "ACCT_SUB_TYPE", nullable = true, length = 25)
    private String acctSubType = "";
    
    @Column(name = "ACCT_CATE", nullable = true, length = 25)
    private String acctCategory = "";
    
    @Column(name = "ACCT_STATUS", nullable = true, length = 25)
    private String acctStatus = "";
    
    @Column(name = "ACCT_BASE_BR", nullable = true, length = 25)
    private String acctBaseBr = ""; // From LOOKUP(TITLE)-USER e.g.: Mr., Ms., Dr., etc.
    
    @Column(name = "ACCT_CREATED_DT", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date acctCreatedDt;
    
    @Column(name = "ACCT_CLOSED_DT", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date acctClosedDt;
    
    @Column(name = "ACCT_OPRN_MODE", nullable = true)
    private Double acctOprnMode;
    
    @Column(name = "ACCT_HAS_NOMINEE", nullable = true)
    private Integer acctHasNominee;
    
    @Column(name = "ACCT_PROD_CODE", nullable = true, length = 25)
    private String acctPrdCode = "";
    
    @Column(name = "STATUS", nullable = true, length = 25)
    private String status;
    
    @Column(name = "REASON", nullable = true, length = 25)
    private String reason;
    
    @Column(name = "ACCOUNT_LOG_ID", nullable = true, length = 25)
    private long accountLogId;
    
    public String getCustCode() {
        return custCode;
    }
    
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public String getAcctNo() {
        return acctNo;
    }
    
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
    
    public Integer getUploadCount() {
        return uploadCount;
    }
    
    public void setUploadCount(Integer uploadCount) {
        this.uploadCount = uploadCount;
    }
    
    public Date getUploadDateTime() {
        return uploadDateTime;
    }
    
    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
    
    public String getAcctType() {
        return acctType;
    }
    
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
    
    public String getAcctSubType() {
        return acctSubType;
    }
    
    public void setAcctSubType(String acctSubType) {
        this.acctSubType = acctSubType;
    }
    
    public String getAcctCategory() {
        return acctCategory;
    }
    
    public void setAcctCategory(String acctCategory) {
        this.acctCategory = acctCategory;
    }
    
    public String getAcctStatus() {
        return acctStatus;
    }
    
    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }
    
    public String getAcctBaseBr() {
        return acctBaseBr;
    }
    
    public void setAcctBaseBr(String acctBaseBr) {
        this.acctBaseBr = acctBaseBr;
    }
    
    public Date getAcctCreatedDt() {
        return acctCreatedDt;
    }
    
    public void setAcctCreatedDt(Date acctCreatedDt) {
        this.acctCreatedDt = acctCreatedDt;
    }
    
    public Date getAcctClosedDt() {
        return acctClosedDt;
    }
    
    public void setAcctClosedDt(Date acctClosedDt) {
        this.acctClosedDt = acctClosedDt;
    }
    
    public Double getAcctOprnMode() {
        return acctOprnMode;
    }
    
    public void setAcctOprnMode(Double acctOprnMode) {
        this.acctOprnMode = acctOprnMode;
    }
    
    public Integer getAcctHasNominee() {
        return acctHasNominee;
    }
    
    public void setAcctHasNominee(Integer acctHasNominee) {
        this.acctHasNominee = acctHasNominee;
    }
    
    public String getAcctPrdCode() {
        return acctPrdCode;
    }
    
    public void setAcctPrdCode(String acctPrdCode) {
        this.acctPrdCode = acctPrdCode;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public long getAccountLogId() {
        return accountLogId;
    }
    
    public void setAccountLogId(long accountLogId) {
        this.accountLogId = accountLogId;
    }
    
    @Override
    public String toString() {
        return "AccountFail [custCode=" + custCode + ", acctNo=" + acctNo + ", uploadCount=" + uploadCount
                + ", uploadDateTime=" + uploadDateTime + ", acctType=" + acctType + ", acctSubType=" + acctSubType
                + ", acctCategory=" + acctCategory + ", acctStatus=" + acctStatus + ", acctBaseBr=" + acctBaseBr
                + ", acctCreatedDt=" + acctCreatedDt + ", acctClosedDt=" + acctClosedDt + ", acctOprnMode="
                + acctOprnMode + ", acctHasNominee=" + acctHasNominee + ", acctPrdCode=" + acctPrdCode + ", status="
                + status + ", reason=" + reason + ", accountLogId=" + accountLogId + "]";
    }
    
}
