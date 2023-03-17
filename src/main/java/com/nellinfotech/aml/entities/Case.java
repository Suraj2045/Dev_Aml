package com.nellinfotech.aml.entities;

import javax.persistence.Column;
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
@Table(name = "Cases")
public class Case extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8364041914615011875L;
    
	@Column(name = "BRANCH_CODE", length = 20, nullable = true)
	private String branchCode="";
	
	@Column(name = "ALERT_CODE", length = 20, nullable = true)
	private String alertCode="";
	
	@Column(name = "ALERTSUBTYPE_CODE", length = 20, nullable = true)
	private String alertSubTypeCode="";
	
	@Column(name = "CASE_ID", length = 20, nullable = true)
	private String caseID="";
	
	@Column(name = "REPORT_TYPE", length = 20, nullable = true)
	private String reportTYpe="";
	
	@Column(name = "CASE_SEVERITY", length = 20, nullable = true)
	private String caseSeverity="";
	
	@Column(name = "CLASSIFICATION", length = 50, nullable = true)
	private String classification="";
	
	@Column(name = "DESCRIPTION", length = 250, nullable = true)
	private String description="";
	
	@Column(name = "RECORDS_REFERRED", length = 20, nullable = false)
	private String recordsReferred="";
	
	@Column(name = "MONEY_LAUNDERING", length = 20, nullable = true)
	private String moneyLaundering	="";
	
	@Column(name = "STRUCTRING", length = 20, nullable = true)
	private String 	structuring="";
	
	@Column(name = "TERROIST_FINANCING", length = 20, nullable = true)
	private String terroristFinancing	="";

	@Column(name = "OTHER_ACTIVITY", length = 20, nullable = true)
	private String 	otherActivity="";

	@Column(name = "INTERNET", length = 20, nullable = true)
	private String 	internet="";

	@Column(name = "MOBILE", length = 20, nullable = true)
	private String 	mobile="";

	@Column(name = "IVR", length = 20, nullable = true)
	private String 	ivr="";

	@Column(name = "POS", length = 20, nullable = true)
	private String 	pos="";

	@Column(name = "INSTRUMENT", length = 20, nullable = true)
	private String 	instrument="";

	@Column(name = "TELLER", length = 20, nullable = true)
	private String 	teller="";

	@Column(name = "ATM", length = 20, nullable = true)
	private String 	atm="";

	@Column(name = "OTHER_CHANNEL", length = 20, nullable = true)
	private String otherChannel="";

	@Column(name = "MULTIPLE_VIOLATION", length = 20, nullable = true)
	private String 	multipleViolations="";

	@Column(name = "SUSPICIOUS_CUSTOMER", length = 20, nullable = true)
	private String 	suspiciousCustomer="";
	
	@Column(name = "HABITUAL_OFFENDER", length = 20, nullable = true)
	private String 	habitualOffender="";

	@Column(name = "FIRST_TIMER", length = 20, nullable = true)
	private String 	firstTimer="";

	@Column(name = "SUSPICIOUS_LINKS", length = 20, nullable = true)
	private String 	suspiciousLinks="";

	@Column(name = "WATCHLIST_IDENTIFIED", length = 20, nullable = true)
	private String 	watchlistIdetified="";

	@Column(name = "ASSOCIATE_LINKS", length = 20, nullable = true)
	private String 	associateLinks="";

	@Column(name = "UNUSAL_HIGH_VOLUME_ALERT", length = 20, nullable = true)
	private String 	unusalHighVolumeAlert="";

	@Column(name = "UNUSAL_HIGH_CASE_ALERT", length = 20, nullable = true)
	private String 	unusalHighCaseeAlert="";
	
	@Column(name = "REESTABLISHED_IDENTIFIED", length = 20, nullable = true)
	private String 	reestablishedIdentified="";
	
	@Column(name = "HIGH_RISK_PEER_GROUP", length = 20, nullable = true)
	private String 	highRiskPeerGroup="";
	
	@Column(name = "PLOTICALLY_EXPOSED", length = 20, nullable = true)
	private String 	politicallyExposed="";
	
	@Column(name = "COMMENTS", length = 250, nullable = true)
	private String 	comments="";

	@Column(name = "FILE_LINK", length = 20, nullable = true)
	private String 	fileLink="";
	
	@Column(name = "ASSIGNED_TO", length = 20, nullable = true)
	private String 	assignedTo="";
	
	@Column(name = "ASSIGNED_TO_NAME", length = 20, nullable = true)
	private String 	assignedToName="";
	
	@Column(name = "CUST_CODE", length = 20, nullable = true)
	private String custId="";
	
	@Column(name = "ACCOUNT_ID", length = 20, nullable = true)
	private String accountId="";
	
	@Column(name = "CUSTOMER_NAME", length = 20, nullable = true)
	private String customerName="";
	
	@Column(name = "CUSTOMER_RISK_LEVEL", length = 20, nullable = true)
	private String customerRiskLevel="";
	
	@Column(name = "CONFIRMED_CASE", length = 20, nullable = true)
	private String confirmedCase="";
	
	@Column(name = "ALERT_ID", length = 20, nullable = true)
	private String alertID="";
	
	
	@Column(name = "CASE_STATUS", length = 20, nullable = true)
	private String caseStatus="";
	
	@Column(name = "SCENARIO_ID", length = 20, nullable = true)
	private String scenarioID="";
	
	@Column(name = "CONFIRMED_FRAUD", length = 20, nullable = true)
	private String confirmedFraud="";
	
	@Column(name = "CREATEDBY_USERNAME", length = 20, nullable = true)
	private String createdByUserName="";
	


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


	public String getCaseID() {
		return caseID;
	}


	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}


	public String getReportTYpe() {
		return reportTYpe;
	}


	public void setReportTYpe(String reportTYpe) {
		this.reportTYpe = reportTYpe;
	}


	public String getCaseSeverity() {
		return caseSeverity;
	}


	public void setCaseSeverity(String caseSeverity) {
		this.caseSeverity = caseSeverity;
	}


	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getRecordsReferred() {
		return recordsReferred;
	}


	public void setRecordsReferred(String recordsReferred) {
		this.recordsReferred = recordsReferred;
	}


	public String getMoneyLaundering() {
		return moneyLaundering;
	}


	public void setMoneyLaundering(String moneyLaundering) {
		this.moneyLaundering = moneyLaundering;
	}


	public String getStructuring() {
		return structuring;
	}


	public void setStructuring(String structuring) {
		this.structuring = structuring;
	}


	public String getTerroristFinancing() {
		return terroristFinancing;
	}


	public void setTerroristFinancing(String terroristFinancing) {
		this.terroristFinancing = terroristFinancing;
	}


	public String getOtherActivity() {
		return otherActivity;
	}


	public void setOtherActivity(String otherActivity) {
		this.otherActivity = otherActivity;
	}


	public String getInternet() {
		return internet;
	}


	public void setInternet(String internet) {
		this.internet = internet;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getIvr() {
		return ivr;
	}


	public void setIvr(String ivr) {
		this.ivr = ivr;
	}


	public String getPos() {
		return pos;
	}


	public void setPos(String pos) {
		this.pos = pos;
	}


	public String getInstrument() {
		return instrument;
	}


	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}


	public String getTeller() {
		return teller;
	}


	public void setTeller(String teller) {
		this.teller = teller;
	}


	public String getAtm() {
		return atm;
	}


	public void setAtm(String atm) {
		this.atm = atm;
	}


	public String getOtherChannel() {
		return otherChannel;
	}


	public void setOtherChannel(String otherChannel) {
		this.otherChannel = otherChannel;
	}


	public String getMultipleViolations() {
		return multipleViolations;
	}


	public void setMultipleViolations(String multipleViolations) {
		this.multipleViolations = multipleViolations;
	}


	public String getSuspiciousCustomer() {
		return suspiciousCustomer;
	}


	public void setSuspiciousCustomer(String suspiciousCustomer) {
		this.suspiciousCustomer = suspiciousCustomer;
	}


	public String getHabitualOffender() {
		return habitualOffender;
	}


	public void setHabitualOffender(String habitualOffender) {
		this.habitualOffender = habitualOffender;
	}


	public String getFirstTimer() {
		return firstTimer;
	}


	public void setFirstTimer(String firstTimer) {
		this.firstTimer = firstTimer;
	}


	public String getSuspiciousLinks() {
		return suspiciousLinks;
	}


	public void setSuspiciousLinks(String suspiciousLinks) {
		this.suspiciousLinks = suspiciousLinks;
	}


	public String getWatchlistIdetified() {
		return watchlistIdetified;
	}


	public void setWatchlistIdetified(String watchlistIdetified) {
		this.watchlistIdetified = watchlistIdetified;
	}


	public String getAssociateLinks() {
		return associateLinks;
	}


	public void setAssociateLinks(String associateLinks) {
		this.associateLinks = associateLinks;
	}


	public String getUnusalHighVolumeAlert() {
		return unusalHighVolumeAlert;
	}


	public void setUnusalHighVolumeAlert(String unusalHighVolumeAlert) {
		this.unusalHighVolumeAlert = unusalHighVolumeAlert;
	}


	public String getUnusalHighCaseeAlert() {
		return unusalHighCaseeAlert;
	}


	public void setUnusalHighCaseeAlert(String unusalHighCaseeAlert) {
		this.unusalHighCaseeAlert = unusalHighCaseeAlert;
	}


	public String getReestablishedIdentified() {
		return reestablishedIdentified;
	}


	public void setReestablishedIdentified(String reestablishedIdentified) {
		this.reestablishedIdentified = reestablishedIdentified;
	}


	public String getHighRiskPeerGroup() {
		return highRiskPeerGroup;
	}


	public void setHighRiskPeerGroup(String highRiskPeerGroup) {
		this.highRiskPeerGroup = highRiskPeerGroup;
	}


	public String getPoliticallyExposed() {
		return politicallyExposed;
	}


	public void setPoliticallyExposed(String politicallyExposed) {
		this.politicallyExposed = politicallyExposed;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getFileLink() {
		return fileLink;
	}


	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}


	public String getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}


	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getAlertID() {
		return alertID;
	}


	public void setAlertID(String alertID) {
		this.alertID = alertID;
	}
	
	
	public String getCaseStatus() {
		return caseStatus;
	}


	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	public String getAssignedToName() {
		return assignedToName;
	}


	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}


	public String getCustomerRiskLevel() {
		return customerRiskLevel;
	}


	public void setCustomerRiskLevel(String customerRiskLevel) {
		this.customerRiskLevel = customerRiskLevel;
	}

	public String getConfirmedCase() {
		return confirmedCase;
	}


	public void setConfirmedCase(String confirmedCase) {
		this.confirmedCase = confirmedCase;
	}


	public String getScenarioID() {
		return scenarioID;
	}


	public void setScenarioID(String scenarioID) {
		this.scenarioID = scenarioID;
	}
	
	public String getConfirmedFraud() {
		return confirmedFraud;
	}


	public void setConfirmedFraud(String confirmedFraud) {
		this.confirmedFraud = confirmedFraud;
	}

	public String getCreatedByUserName() {
		return createdByUserName;
	}


	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}


	
//	@Override
//	public String toString() {
//		return "Alert [branchCode=" + branchCode + ", alertCode=" + alertCode + ", alertSubTypeCode=" + alertSubTypeCode
//				+ ", custId=" + custId + ", accountId=" + accountId + ", assignedUserId=" + assignedUserId
//				+ ", location=" + location + ", caseId=" + caseId + ", customerNo=" + customerNo + ", customerName="
//				+ customerName + ", riskSeverity=" + riskSeverity + ", alertStatus=" + alertStatus + ", assignedUser="
//				+ assignedUser + "]";
//	}

	
	
	
    
}
