package com.nellinfotech.aml.dto;

public class InvestigatorPerformance {
	
	private Integer assigned_userid;
	private Double  performancePercent;
	private String assignedUser;

	
	public Integer getAssigned_userid() {
		return assigned_userid;
	}
	public void setAssigned_userid(Integer assigned_userid) {
		this.assigned_userid = assigned_userid;
	}
	
	
	
	public String getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}
	
	public Double getPerformancePercent() {
		return performancePercent;
	}
	public void setPerformancePercent(Double performancePercent) {
		this.performancePercent = performancePercent;
	}
	@Override
	public String toString() {
		return "InvestigatorPerformance [assigned_userid=" + assigned_userid + ", performancePercent="
				+ performancePercent + ", assignedUser=" + assignedUser + "]";
	}
	
	
	
}
