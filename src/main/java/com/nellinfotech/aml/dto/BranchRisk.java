package com.nellinfotech.aml.dto;

import java.math.BigDecimal;

public class BranchRisk {

	private String branchCode;
	private Long alertCount;
	private Long confirmCount;
	private BigDecimal fraudAmount;
	private String branchName;
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public Long getAlertCount() {
		return alertCount;
	}
	public void setAlertCount(Long alertCount) {
		this.alertCount = alertCount;
	}
	public Long getConfirmCount() {
		return confirmCount;
	}
	public void setConfirmCount(Long confirmCount) {
		this.confirmCount = confirmCount;
	}
	public BigDecimal getFraudAmount() {
		return fraudAmount;
	}
	public void setFraudAmount(BigDecimal fraudAmount) {
		this.fraudAmount = fraudAmount;
	}
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Override
	public String toString() {
		return "BranchRisk [branchCode=" + branchCode + ", alertCount=" + alertCount + ", confirmCount=" + confirmCount
				+ ", fraudAmount=" + fraudAmount + ", branchName=" + branchName + "]";
	}
	
}
