package com.nellinfotech.aml.entities;

import java.math.BigDecimal;
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
@Table(name = "Transaction")
public class Transaction extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 5698921815722735358L;
    
    @Column(name = "CUST_CODE", nullable = false, length = 25)
    
    private String custCode = "";
    
    @Column(name = "ACCT_NO", nullable = false, length = 50)
    
    private String acctNo = "";
    
    @Column(name = "RULE_EXECUTED", nullable = false)
    
    private Integer ruleExecuted;
    
    // Read from Sequence Table
    
    @Column(name = "UPLOAD_COUNT", nullable = true)
    
    private Integer uploadCount;
    
    @Column(name = "TXN_NO", nullable = false, length = 50)
    
    private String txnNo = "";
    
    @Column(name = "TXN_CODE", nullable = false, length = 50)
    
    private String txnCode = "";
    
    @Column(name = "TXN_SET_NO", nullable = false, length = 50)
    
    private String txnSetNo = "";
    
    @Column(name = "TXN_SCROLL_NO", nullable = false, length = 50)
    
    private String txnScrollNo = "";
    
    @Column(name = "TXN_TOKEN_NO", nullable = false, length = 50)
    
    private String txnTokenNo = "";
    
    @Column(name = "TXN_BATCH_CODE", nullable = false, length = 50)
    
    private String txnBatchCode = "";
    
    @Column(name = "UPLOAD_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date uploadDateTime = Calendar.getInstance().getTime();
    
    @Column(name = "TXN_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date txnDtTm;
    
    @Column(name = "TXN_VALUE_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date valueDtTm;
    
    @Column(name = "TXN_POST_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date postingDtTm;
    
    @Column(name = "TXN_DRCR", nullable = true, length = 10)
    
    private String drCr = "";
    
    @Column(name = "TXN_TYPE", nullable = true, length = 25)
    
    private String txnType = "";
    
    @Column(name = "TXN_SUBTYPE", nullable = true, length = 25)
    
    private String txnSubType = "";
    
    @Column(name = "CASHFLOW_TYPE", nullable = true, length = 25)
    
    private String cashflowType = "";
    
    @Column(name = "TXN_DR_PARTYNAMEE", nullable = true, length = 25)
    
    private String drPartyName = "";
    
    @Column(name = "TXN_CR_PARTYNAMEE", nullable = true, length = 25)
    
    private String crPartyName = "";
    
    @Column(name = "TXN_AMT", nullable = true, precision = 16, scale = 4)
    
    private BigDecimal txnAmount;
    
    @Column(name = "AC_BAL_AMT", nullable = true, precision = 16, scale = 4)
    
    private BigDecimal acBalAmt;
    
    @Column(name = "TXN_CHARGES", nullable = true, precision = 16, scale = 4)
    
    private BigDecimal txnCharges;
    
    @Column(name = "TXN_BR_CODE", nullable = true, length = 25)
    
    private String txnBranchCode;
    
    @Column(name = "TXN_STATUS", nullable = true, length = 25)
    
    private String txnStatus;
    
    @Column(name = "TXN_CREATED_BY", nullable = true, length = 25)
    
    private String txnCreatedBy;
    
    @Column(name = "TXN_CREATED_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date txnCreatedDtTm;
    
    @Column(name = "TXN_AUTH_BY", nullable = true, length = 25)
    
    private String txnAuthBy;
    
    @Column(name = "TXN_AUTH_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date txnAuthDtTm;
    
    @Column(name = "STATUS", nullable = true, length = 25)
    private String status;
    
    @Column(name = "ACCOUNT_LOG_ID", nullable = true, length = 25)
    private long accountLogId;
    
    @Column(name = "channel", nullable = true, length = 100)
    private String channel;
    
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
    
    public Integer getRuleExecuted() {
        return ruleExecuted;
    }
    
    public void setRuleExecuted(Integer ruleExecuted) {
        this.ruleExecuted = ruleExecuted;
    }
    
    public Integer getUploadCount() {
        return uploadCount;
    }
    
    public void setUploadCount(Integer uploadCount) {
        this.uploadCount = uploadCount;
    }
    
    public String getTxnNo() {
        return txnNo;
    }
    
    public void setTxnNo(String txnNo) {
        this.txnNo = txnNo;
    }
    
    public String getTxnCode() {
        return txnCode;
    }
    
    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }
    
    public String getTxnSetNo() {
        return txnSetNo;
    }
    
    public void setTxnSetNo(String txnSetNo) {
        this.txnSetNo = txnSetNo;
    }
    
    public String getTxnScrollNo() {
        return txnScrollNo;
    }
    
    public void setTxnScrollNo(String txnScrollNo) {
        this.txnScrollNo = txnScrollNo;
    }
    
    public String getTxnTokenNo() {
        return txnTokenNo;
    }
    
    public void setTxnTokenNo(String txnTokenNo) {
        this.txnTokenNo = txnTokenNo;
    }
    
    public String getTxnBatchCode() {
        return txnBatchCode;
    }
    
    public void setTxnBatchCode(String txnBatchCode) {
        this.txnBatchCode = txnBatchCode;
    }
    
    public Date getUploadDateTime() {
        return uploadDateTime;
    }
    
    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
    
    public Date getTxnDtTm() {
        return txnDtTm;
    }
    
    public void setTxnDtTm(Date txnDtTm) {
        this.txnDtTm = txnDtTm;
    }
    
    public Date getValueDtTm() {
        return valueDtTm;
    }
    
    public void setValueDtTm(Date valueDtTm) {
        this.valueDtTm = valueDtTm;
    }
    
    public Date getPostingDtTm() {
        return postingDtTm;
    }
    
    public void setPostingDtTm(Date postingDtTm) {
        this.postingDtTm = postingDtTm;
    }
    
    public String getDrCr() {
        return drCr;
    }
    
    public void setDrCr(String drCr) {
        this.drCr = drCr;
    }
    
    public String getTxnType() {
        return txnType;
    }
    
    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }
    
    public String getTxnSubType() {
        return txnSubType;
    }
    
    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }
    
    public String getCashflowType() {
        return cashflowType;
    }
    
    public void setCashflowType(String cashflowType) {
        this.cashflowType = cashflowType;
    }
    
    public String getDrPartyName() {
        return drPartyName;
    }
    
    public void setDrPartyName(String drPartyName) {
        this.drPartyName = drPartyName;
    }
    
    public String getCrPartyName() {
        return crPartyName;
    }
    
    public void setCrPartyName(String crPartyName) {
        this.crPartyName = crPartyName;
    }
    
    public BigDecimal getTxnAmount() {
        return txnAmount;
    }
    
   
    
    public BigDecimal getAcBalAmt() {
		return acBalAmt;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public void setAcBalAmt(BigDecimal acBalAmt) {
        this.acBalAmt = acBalAmt;
    }
    
    public BigDecimal getTxnCharges() {
        return txnCharges;
    }
    
    public void setTxnCharges(BigDecimal txnCharges) {
        this.txnCharges = txnCharges;
    }
    
    public String getTxnBranchCode() {
        return txnBranchCode;
    }
    
    public void setTxnBranchCode(String txnBranchCode) {
        this.txnBranchCode = txnBranchCode;
    }
    
    public String getTxnStatus() {
        return txnStatus;
    }
    
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }
    
    public String getTxnCreatedBy() {
        return txnCreatedBy;
    }
    
    public void setTxnCreatedBy(String txnCreatedBy) {
        this.txnCreatedBy = txnCreatedBy;
    }
    
    public Date getTxnCreatedDtTm() {
        return txnCreatedDtTm;
    }
    
    public void setTxnCreatedDtTm(Date txnCreatedDtTm) {
        this.txnCreatedDtTm = txnCreatedDtTm;
    }
    
    public String getTxnAuthBy() {
        return txnAuthBy;
    }
    
    public void setTxnAuthBy(String txnAuthBy) {
        this.txnAuthBy = txnAuthBy;
    }
    
    public Date getTxnAuthDtTm() {
        return txnAuthDtTm;
    }
    
    public void setTxnAuthDtTm(Date txnAuthDtTm) {
        this.txnAuthDtTm = txnAuthDtTm;
    }
    
    
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public long getAccountLogId() {
        return accountLogId;
    }
    
    public void setAccountLogId(long accountLogId) {
        this.accountLogId = accountLogId;
    }

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "Transaction [custCode=" + custCode + ", acctNo=" + acctNo + ", ruleExecuted=" + ruleExecuted
				+ ", uploadCount=" + uploadCount + ", txnNo=" + txnNo + ", txnCode=" + txnCode + ", txnSetNo="
				+ txnSetNo + ", txnScrollNo=" + txnScrollNo + ", txnTokenNo=" + txnTokenNo + ", txnBatchCode="
				+ txnBatchCode + ", uploadDateTime=" + uploadDateTime + ", txnDtTm=" + txnDtTm + ", valueDtTm="
				+ valueDtTm + ", postingDtTm=" + postingDtTm + ", drCr=" + drCr + ", txnType=" + txnType
				+ ", txnSubType=" + txnSubType + ", cashflowType=" + cashflowType + ", drPartyName=" + drPartyName
				+ ", crPartyName=" + crPartyName + ", txnAmount=" + txnAmount + ", acBalAmt=" + acBalAmt
				+ ", txnCharges=" + txnCharges + ", txnBranchCode=" + txnBranchCode + ", txnStatus=" + txnStatus
				+ ", txnCreatedBy=" + txnCreatedBy + ", txnCreatedDtTm=" + txnCreatedDtTm + ", txnAuthBy=" + txnAuthBy
				+ ", txnAuthDtTm=" + txnAuthDtTm + ", status=" + status + ", accountLogId=" + accountLogId
				+ ", channel=" + channel + "]";
	}

	
	
}
