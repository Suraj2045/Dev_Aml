package com.nellinfotech.aml.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "EOD_Master")
public class EODMst extends BaseEntity {

	  @Column(name = "PROCESS_NAME", length = 50, nullable = false)
	    private String processName;
	    
	  @Column(name = "PROCESS_DESCRIPTION", length = 150, nullable = false)
	    private String processDescription;
	  
	  @Column(name = "PROCESS_ID", length = 50, nullable = false)
	    private String processId;
	  
	   @Column(name = "LAST_RUN_DATE")
	    private String lastRunDate;
	   
	   @Column(name = "NEXT_RUN_DATE")
	    private String nextRunDate;
	   
	   @Column(name = "FREQUENCY", length = 50, nullable = false)
	    private String frequency;
	   
	   @Column(name = "UPLOAD", length = 50, nullable = true)
	    private String upload;
	   
	   
	   
	  public String getProcessName() {
		return processName;
	}



	public void setProcessName(String processName) {
		this.processName = processName;
	}



	public String getProcessDescription() {
		return processDescription;
	}



	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}



	public String getProcessId() {
		return processId;
	}



	public void setProcessId(String processId) {
		this.processId = processId;
	}



	public String getLastRunDate() {
		return lastRunDate;
	}



	public void setLastRunDate(String lastRunDate) {
		this.lastRunDate = lastRunDate;
	}



	public String getNextRunDate() {
		return nextRunDate;
	}



	public void setNextRunDate(String nextRunDate) {
		this.nextRunDate = nextRunDate;
	}



	public String getFrequency() {
		return frequency;
	}



	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}



	public String getUpload() {
		return upload;
	}



	public void setUpload(String upload) {
		this.upload = upload;
	}



	@Override
	    public String toString() {
	        return "EODMst [processName=" + processName + ", processDescription=" + processDescription + ", processId=" + processId + ", frequency=" + frequency +", upload=" + upload +"]";
	    }}
