package com.nellinfotech.aml.dto;

import java.util.Date;

public class Request {
	
	 private Long id;
	 private String travelDateFrom;
	 private String travelDateTo;
	 private String customerNo;
	 private String customerName;
	 private String primaryCardholderName;
	 private String primaryCardholderNo;
	 private String travelLocation;
	 private String specialInstructions;
	 private String travelPurpose;
	 private String limitTransaction;
	 
	public String getTravelDateFrom() {
		return travelDateFrom;
	}
	public void setTravelDateFrom(String travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}
	public String getTravelDateTo() {
		return travelDateTo;
	}
	public void setTravelDateTo(String travelDateTo) {
		this.travelDateTo = travelDateTo;
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
	public String getPrimaryCardholderName() {
		return primaryCardholderName;
	}
	public void setPrimaryCardholderName(String primaryCardholderName) {
		this.primaryCardholderName = primaryCardholderName;
	}
	public String getPrimaryCardholderNo() {
		return primaryCardholderNo;
	}
	public void setPrimaryCardholderNo(String primaryCardholderNo) {
		this.primaryCardholderNo = primaryCardholderNo;
	}
	public String getTravelLocation() {
		return travelLocation;
	}
	public void setTravelLocation(String travelLocation) {
		this.travelLocation = travelLocation;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getTravelPurpose() {
		return travelPurpose;
	}
	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}
	public String getLimitTransaction() {
		return limitTransaction;
	}
	public void setLimitTransaction(String limitTransaction) {
		this.limitTransaction = limitTransaction;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	 
	 
}
