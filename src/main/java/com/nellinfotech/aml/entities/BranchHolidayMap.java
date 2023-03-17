package com.nellinfotech.aml.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "Branch_Holiday_Map")
public class BranchHolidayMap extends BaseEntity implements Serializable{
	
	
	
	private static final long serialVersionUID = 1818877876406543880L;

	
	@Column(name = "BRANCH_CODE", length = 20, nullable = false)
	private String branchCode="";
	
	@Column(name = "HOLIDAY_CODE", length = 20, nullable = false)
	private String holidayCode="";
	
	@Column(name = "HOLIDAY_DESCRIPTION", length = 20, nullable = false)
	private String holidayDesc="";
	
	@Column(name = "HOLIDAY_DATE", length = 100, nullable = false)
	
	private String holidayDate;
	
	
	
	
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getHolidayCode() {
		return holidayCode;
	}
	public void setHolidayCode(String holidayCode) {
		this.holidayCode = holidayCode;
	}
	public String getHolidayDesc() {
		return holidayDesc;
	}
	public void setHolidayDesc(String holidayDesc) {
		this.holidayDesc = holidayDesc;
	}
	public String getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}
	
	
	

}
