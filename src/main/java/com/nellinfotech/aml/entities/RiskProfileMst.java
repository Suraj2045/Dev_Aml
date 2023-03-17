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
@Table(name = "riskprofile_Mst")
public class RiskProfileMst extends BaseEntity{
	
	
	private static final long serialVersionUID = 2220046628953539994L;
	

	@Column(name = "BRANCH_CODE", length = 20, nullable = false)
	private String branchCode="";
	
	@Column(name = " RISK_CODe", length = 20, nullable = false)
	private String riskCode="";
	
	@Column(name = "RISK_PARAMETER", length = 20, nullable = false)
	private String riskParameter="";
	
	@Column(name = "RISK_VALUE", length = 20, nullable = false)
	private String riskValue="";
	
	@Column(name = "WEIGHTAGE", length = 20, nullable = false)
	private int weightage;
	

	
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskParameter() {
		return riskParameter;
	}

	public void setRiskParameter(String riskParameter) {
		this.riskParameter = riskParameter;
	}

	public int getWeightage() {
		return weightage;
	}

	public int setWeightage(int weightage) {
		return this.weightage = weightage;
	}

	public String getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(String riskValue) {
		this.riskValue = riskValue;
	}
	
		
		
}
