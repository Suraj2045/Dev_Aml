package com.nellinfotech.aml.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AlertPerformance {
	    
	private Integer assigned_userid;
	private Long closeCount;
	private Long pendingCount;
	private Long totalAssigned;
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
	public Long getCloseCount() {
		return closeCount;
	}
	public void setCloseCount(Long closeCount) {
		this.closeCount = closeCount;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getTotalAssigned() {
		return totalAssigned;
	}
	public void setTotalAssigned(Long totalAssigned) {
		this.totalAssigned = totalAssigned;
	}
	@Override
	public String toString() {
		return "AlertPerformance [assigned_userid=" + assigned_userid + ", closeCount=" + closeCount + ", pendingCount="
				+ pendingCount + ", totalAssigned=" + totalAssigned + ", assignedUser=" + assignedUser + "]";
	}
	
	
	
	
    
}
