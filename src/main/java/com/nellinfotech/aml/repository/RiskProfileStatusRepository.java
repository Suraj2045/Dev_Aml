package com.nellinfotech.aml.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.entities.RiskProfileStatus;

public interface RiskProfileStatusRepository extends JpaRepository<RiskProfileStatus, Long> {

	
	@Query(value = " SELECT * FROM riskprofile_status  WHERE branch_code=:branchCode and is_active=1 and cust_code=:custCode", nativeQuery = true) 
	List<RiskProfileStatus> getRiskStatus(String branchCode, String custCode);

	@Query(value = " SELECT * FROM riskprofile_status  WHERE cust_code=:custCode and is_active=1 and (created_date BETWEEN (:fromDate) and (:toDate))", nativeQuery = true)
	List<RiskProfileStatus> getRiskMovement(String custCode, String fromDate, String toDate);
	
//	@Query(value = " SELECT * FROM riskprofile_mst  WHERE branch_code=:branchCode and risk_code='KYC'", nativeQuery = true)
//	List<RiskProfileMst> getKYCbyBranchCode(String branchCode);

	List<RiskProfileStatus> getRiskStatusByBranchCode(String branchCode);

	@Modifying
	@Transactional
	@Query(value = " insert into riskprofile_status (branch_code, bank_code, created_date, is_active, version, cust_code, kyc_risk, transactiontype_risk, transactiontrend_risk, violation_risk, risk_score, auth_status) VALUES "
			+ " (:branchCode, :bankCode, :createdDate, :isActive, :version, :custCode, :kycRisk, :transactioTypeRisk, :transactiontrendRisk, :violationRisk, :riskScore, :authStatus) ", nativeQuery = true)
	void saveRiskProfileStatus(String branchCode, String bankCode, Date createdDate, Integer isActive, Integer version,
			String custCode, BigDecimal kycRisk, BigDecimal transactioTypeRisk, BigDecimal transactiontrendRisk,
			BigDecimal violationRisk, BigDecimal riskScore, Integer authStatus);

	

}
