package com.nellinfotech.aml.entities;

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
@Table(name = "Lookup_Mst")
public class LookupMst extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7074914444158918701L;

	
	@Column(name = "LOOKUP_CODE", length = 20, nullable = false)
	private String lookupCode="";   // Gender, BrType 
	
	@Column(name = "LOOKUP_DESCRIPTION", length = 20, nullable = false)
	private String lookupDesc="";
	
	@Column(name = "LOOKUP_TYPE", length = 20, nullable = false)
	private String lookupType="";   // system/user
	
	
	
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public String getLookupDesc() {
		return lookupDesc;
	}
	public void setLookupDesc(String lookupDesc) {
		this.lookupDesc = lookupDesc;
	}
	public String getLookupType() {
		return lookupType;
	}
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
	
	
	
	

}
