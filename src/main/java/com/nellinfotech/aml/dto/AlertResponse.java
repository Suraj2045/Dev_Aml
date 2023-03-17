package com.nellinfotech.aml.dto;

public class AlertResponse {
	private String attribute;
	private String value;
	private String violation_Parameter;
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getViolation_Parameter() {
		return violation_Parameter;
	}
	public void setViolation_Parameter(String violation_Parameter) {
		this.violation_Parameter = violation_Parameter;
	}
	
	
}
