package com.nellinfotech.aml.dto;

public class AlertStatistics {
	
	 private String alertName;
	 private Integer totalCount = 0;
	 private Integer dayCount=0;
	 private Integer prevCount=0;
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getDayCount() {
		return dayCount;
	}
	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}
	public Integer getPrevCount() {
		return prevCount;
	}
	public void setPrevCount(Integer prevCount) {
		this.prevCount = prevCount;
	}
	@Override
	public String toString() {
		return "AlertStatistics [alertName=" + alertName + ", totalCount=" + totalCount + ", dayCount=" + dayCount
				+ ", prevCount=" + prevCount + "]";
	}
	
	
}
