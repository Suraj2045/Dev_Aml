package com.nellinfotech.aml.dto;

public class TemplateDTO {

	 private String name;
	 private Integer totalCount = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "TemplateDTO [name=" + name + ", totalCount=" + totalCount + "]";
	}
	
}
