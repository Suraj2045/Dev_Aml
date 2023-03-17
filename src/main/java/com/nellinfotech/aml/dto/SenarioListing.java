package com.nellinfotech.aml.dto;

public class SenarioListing {

	private String scenario_description;
	private String customerNo="";
	private String customerName="";
	private String riskSeverity="";
	public String getScenario_description() {
		return scenario_description;
	}
	public void setScenario_description(String scenario_description) {
		this.scenario_description = scenario_description;
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
	@Override
	public String toString() {
		return "SenarioListing [scenario_description=" + scenario_description + ", customerNo=" + customerNo
				+ ", customerName=" + customerName + ", riskSeverity=" + riskSeverity + "]";
	}
}
