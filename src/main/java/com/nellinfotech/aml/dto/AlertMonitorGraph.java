package com.nellinfotech.aml.dto;

public class AlertMonitorGraph {

	private Integer count;
	private String alertsubtype_code;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getAlertsubtype_code() {
		return alertsubtype_code;
	}
	public void setAlertsubtype_code(String alertsubtype_code) {
		this.alertsubtype_code = alertsubtype_code;
	}
	@Override
	public String toString() {
		return "AlertMonitorGraph [count=" + count + ", alertsubtype_code=" + alertsubtype_code + "]";
	}
	
}
