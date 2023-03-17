package com.nellinfotech.aml.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
@Table(name = "Alert")
public class Alert extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8364041914615011875L;
    @Column(name = "BRANCH_CODE", length = 20, nullable = false)
	private String branchCode="";
	
	@Column(name = "ALERT_CODE", length = 20, nullable = false)
	private String alertCode="";
	
	@Column(name = "ALERTSUBTYPE_CODE", length = 20, nullable = false)
	private String alertSubTypeCode="";
	
	@Column(name = "BaseRule_Id", length = 20, nullable = false)
	private Long baseRuleId;
	
	@Column(name = "CUST_ID", length = 20, nullable = false)
	private String custId="";
	
	@Column(name = "ACCOUNT_NO", length = 20, nullable = false)
	private String accountNo="";
	
	@Column(name = "ASSIGNED_USERID", length = 20, nullable = false)
	private String assignedUserId="";
	
	@Column(name = "LOCATION", length = 20, nullable = false)
	private String location="";
	
	@Column(name = "CASE_ID", length = 20, nullable = false)
	private String caseId="";

	@Column(name = "CUSTOMER_NO", length = 20, nullable = false)
	private String customerNo="";

	@Column(name = "CUSTOMER_NAME", length = 20, nullable = false)
	private String customerName="";
	
	@Column(name = "RISK_SEVERITY", length = 20, nullable = false)
	private String riskSeverity="";
	
	@Column(name = "ALERT_STATUS", length = 200, nullable = false)
	private String alertStatus="";
	
	@Column(name = "ASSIGNED_USER", length = 20, nullable = false)
	private String assignedUser="";
	
	@Column(name = "ALERT_Percent", length = 10, nullable = true)
	private double alertPercent;
	
	@Column(name = "CHANNEL", length = 10, nullable = true)
	private String chennel="";

	//transaction details
	 @Column(name = "TXN_NO", nullable = false, length = 50) 
	  private String txnNo = "";
	 
	 @Column(name = "TXN_AMT", nullable = true, precision = 16, scale = 4)
	 private BigDecimal txnAmount;
	 
	 @Column(name = "TXN_DATETIME", nullable = true)
	 @Temporal(TemporalType.TIMESTAMP)
      private Date txnDtTm;
	 
	 @Column(name = "CURRENCY", length = 10, nullable = true)
	  private String currency="";
	 
	 @Column(name = "CASHFLOW_TYPE", nullable = true, length = 25)    
	  private String cashflowType = "";
	 
	 @Column(name = "TXN_TYPE", nullable = true, length = 25)  
	  private String txnType = "";
	 
	 @Column(name = "REMARK", nullable = true, length = 25)  
	  private String remark = "";

	 @Transient
	 @Column(name = "wipcount")
	  private String wipcount;
	 
	 @Transient
	 @Column(name = "closecount")
	  private String closecount;
	 
	 
	 
	


	
	public String getWipcount() {
		return wipcount;
	}


	public void setWipcount(String wipcount) {
		this.wipcount = wipcount;
	}


	public String getClosecount() {
		return closecount;
	}


	public void setClosecount(String closecount) {
		this.closecount = closecount;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertSubTypeCode() {
		return alertSubTypeCode;
	}

	public void setAlertSubTypeCode(String alertSubTypeCode) {
		this.alertSubTypeCode = alertSubTypeCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getChennel() {
		return chennel;
	}

	public void setChennel(String chennel) {
		this.chennel = chennel;
	}

	public String getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRiskSeverity() {
		return riskSeverity;
	}

	public void setRiskSeverity(String riskSeverity) {
		this.riskSeverity = riskSeverity;
	}

	public String getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	public String getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}
	
		public double getAlertPercent() {
		return alertPercent;
	}

	public void setAlertPercent(double closedCountPercent) {
		this.alertPercent = closedCountPercent;
	}

	public String getTxnNo() {
		return txnNo;
	}

	public void setTxnNo(String txnNo) {
		this.txnNo = txnNo;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public Date getTxnDtTm() {
		return txnDtTm;
	}

	public void setTxnDtTm(Date txnDtTm) {
		this.txnDtTm = txnDtTm;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCashflowType() {
		return cashflowType;
	}

	public void setCashflowType(String cashflowType) {
		this.cashflowType = cashflowType;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Long getBaseRuleId() {
		return baseRuleId;
	}
	public void setBaseRuleId(Long baseRuleId) {
		this.baseRuleId = baseRuleId;
	}
	@Override
	public String toString() {
		return "Alert [branchCode=" + branchCode + ", alertCode=" + alertCode + ", baseRuleId=" + baseRuleId
				+ ", alertSubTypeCode=" + alertSubTypeCode + ", custId=" + custId + ", accountNo=" + accountNo
				+ ", assignedUserId=" + assignedUserId + ", location=" + location + ", caseId=" + caseId
				+ ", customerNo=" + customerNo + ", customerName=" + customerName + ", riskSeverity=" + riskSeverity
				+ ", alertStatus=" + alertStatus + ", assignedUser=" + assignedUser + ", alertPercent=" + alertPercent
				+ ", chennel=" + chennel + ", txnNo=" + txnNo + ", txnAmount=" + txnAmount + ", txnDtTm=" + txnDtTm
				+ ", currency=" + currency + ", cashflowType=" + cashflowType + ", txnType=" + txnType + "]";
	}
//	public Alert( String wipCount, String closeCount) {
//	    this.wipCount = wipCount;
//	    this.closeCount = closeCount;
//	}

	
    
}
