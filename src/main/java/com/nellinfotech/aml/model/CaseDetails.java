package com.nellinfotech.aml.model;

public class CaseDetails {
	
	private String assignedTo = "";
	private String assignedToName="";
	
	
	 public CaseDetails(String assignedTo,String assignedToName){
	        this.assignedTo=assignedTo;
	        this.assignedToName=assignedToName;
	    }
	 
	public String getAssignedTo() {
		return assignedTo;
	}
	public String getAssignedToName() {
		return assignedToName;
	}
	
	
	

}
