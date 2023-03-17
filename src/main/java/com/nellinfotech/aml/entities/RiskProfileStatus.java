package com.nellinfotech.aml.entities;

import java.math.BigDecimal;

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
@Table(name = "riskprofile_status")
public class RiskProfileStatus extends BaseEntity{
	
	
	private static final long serialVersionUID = 2220046628953539994L;
	

	@Column(name = "BRANCH_CODE", length = 20, nullable = false)
	private String branchCode="";
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	@Column(name = "CUST_CODE", length = 20, nullable = false)
	private String custCode;
	
	@Column(name = " KYC_RISK", length = 20, nullable = false)
	private BigDecimal kycRisk;
	
	@Column(name = "TRANSACTIONTYPE_RISK", length = 20, nullable = false)
	private BigDecimal transactioTypeRisk;
	
	@Column(name = "TRANSACTIONTREND_RISK", length = 20, nullable = false)
	private BigDecimal transactiontrendRisk;
	
	@Column(name = "VIOLATION_RISK", length = 20, nullable = false)
	private BigDecimal violationRisk;
	
	@Column(name = "RISK_SCORE", length = 20, nullable = false)
	private BigDecimal riskScore;
	
	public BigDecimal getKycRisk() {
		return kycRisk;
	}

	public void setKycRisk(BigDecimal kycRisk) {
		this.kycRisk = kycRisk;
	}

	public BigDecimal getTransactioTypeRisk() {
		return transactioTypeRisk;
	}

	public void setTransactioTypeRisk(BigDecimal transactioTypeRisk) {
		this.transactioTypeRisk = transactioTypeRisk;
	}

	public BigDecimal getTransactiontrendRisk() {
		return transactiontrendRisk;
	}

	public void setTransactiontrendRisk(BigDecimal transactiontrendRisk) {
		this.transactiontrendRisk = transactiontrendRisk;
	}

	public BigDecimal getViolationRisk() {
		return violationRisk;
	}

	public void setViolationRisk(BigDecimal violationRisk) {
		this.violationRisk = violationRisk;
	}
	

	public BigDecimal getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(BigDecimal riskScore) {
		this.riskScore = riskScore;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}




	

	


	

	
			
		
}
