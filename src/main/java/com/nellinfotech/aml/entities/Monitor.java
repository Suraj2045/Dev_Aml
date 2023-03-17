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
@Table(name = "MONITOR")
public class Monitor extends BaseEntity{

	private String receivedOn;
	private String terminalId ;
	private String merchantName ;
	private String cppDate;
	private String merchantLocation;
	private String identifiedDate;
	private String mcc ;
	private String fraudLocation  ;
	private String acquirerBank  ;
	private String detectionSource;
	private String merchantIdNo;
	private String ipAddress;
	public String getReceivedOn() {
		return receivedOn;
	}
	public void setReceivedOn(String receivedOn) {
		this.receivedOn = receivedOn;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getCppDate() {
		return cppDate;
	}
	public void setCppDate(String cppDate) {
		this.cppDate = cppDate;
	}
	public String getMerchantLocation() {
		return merchantLocation;
	}
	public void setMerchantLocation(String merchantLocation) {
		this.merchantLocation = merchantLocation;
	}
	public String getIdentifiedDate() {
		return identifiedDate;
	}
	public void setIdentifiedDate(String identifiedDate) {
		this.identifiedDate = identifiedDate;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getFraudLocation() {
		return fraudLocation;
	}
	public void setFraudLocation(String fraudLocation) {
		this.fraudLocation = fraudLocation;
	}
	public String getAcquirerBank() {
		return acquirerBank;
	}
	public void setAcquirerBank(String acquirerBank) {
		this.acquirerBank = acquirerBank;
	}
	public String getDetectionSource() {
		return detectionSource;
	}
	public void setDetectionSource(String detectionSource) {
		this.detectionSource = detectionSource;
	}
	public String getMerchantIdNo() {
		return merchantIdNo;
	}
	public void setMerchantIdNo(String merchantIdNo) {
		this.merchantIdNo = merchantIdNo;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Override
	public String toString() {
		return "Monitor [receivedOn=" + receivedOn + ", terminalId=" + terminalId + ", merchantName=" + merchantName
				+ ", cppDate=" + cppDate + ", merchantLocation=" + merchantLocation + ", identifiedDate="
				+ identifiedDate + ", mcc=" + mcc + ", fraudLocation=" + fraudLocation + ", acquirerBank="
				+ acquirerBank + ", detectionSource=" + detectionSource + ", merchantIdNo=" + merchantIdNo
				+ ", ipAddress=" + ipAddress + "]";
	}
	
	
}
