package com.nellinfotech.aml.dto;

public class AlertTransaction {

	private String description;
	private String alertId;
	private int scannedTransaction;
	private int violation;
	private double totalAmount;
	private String falsePositiveCount;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public int getScannedTransaction() {
		return scannedTransaction;
	}
	public void setScannedTransaction(int scannedTransaction) {
		this.scannedTransaction = scannedTransaction;
	}
	public int getViolation() {
		return violation;
	}
	public void setViolation(int violation) {
		this.violation = violation;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double total) {
		this.totalAmount = total;
	}
	public String getFalsePositiveCount() {
		return falsePositiveCount;
	}
	public void setFalsePositiveCount(String falsePositiveCount) {
		this.falsePositiveCount = falsePositiveCount;
	}
	
	
	
}
