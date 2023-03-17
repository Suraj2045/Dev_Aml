package com.nellinfotech.aml.entities;

import java.sql.Date;

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
@Table(name = "riskweightage_mst")
public class RiskWeightageMst extends BaseEntity{
	
	
	private static final long serialVersionUID = 2220046628953539994L;
	
	@Column(name = "BRANCH_CODE", length = 20, nullable = false)
	private String branchCode="";
	
	@Column(name = "RISk_CODE", length = 200, nullable = false)
	private String riskCode="";
	
	@Column(name = "WEIGHTAGE", length = 200, nullable = false)
	private String weightage="";
	
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

	public String getWeightage() {
		return weightage;
	}

	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}

	
	
		
}
