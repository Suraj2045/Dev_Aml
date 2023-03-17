package com.nellinfotech.aml.entities;

import java.time.LocalDate;
import java.util.Date;

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
@Table(name = "TRAVEL_LOG_MASTER")
public class TravelLogMst extends BaseEntity  {
	 
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
	@Override
	public String toString() {
		return "TravelLogMst [travelDateFrom=" + travelDateFrom + ", travelDateTo=" + travelDateTo + ", customerNo="
				+ customerNo + ", customerName=" + customerName + ", primaryCardholderName=" + primaryCardholderName
				+ ", primaryCardholderNo=" + primaryCardholderNo + ", travelLocation=" + travelLocation
				+ ", specialInstructions=" + specialInstructions + ", travelPurpose=" + travelPurpose
				+ ", limitTransaction=" + limitTransaction + "]";
	}
	
}
