package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.BranchMst;
import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.entities.RiskProfileStatus;
import com.nellinfotech.aml.entities.RiskWeightageMst;

public interface RiskWeightageMstRepository extends JpaRepository<RiskWeightageMst, Long> {

	@Query(value = " SELECT * FROM riskweightage_mst  WHERE bank_code=:bankCode and is_active=1",nativeQuery = true)
	List<RiskWeightageMst> getWeightage(String bankCode);


	@Query(value = " SELECT * FROM riskprofile_mst  WHERE branch_code=:branchCode and is_active=1 and riskprofile_mst.risk_code='Transaction Type' ",nativeQuery = true)
	List<RiskProfileMst> getTransactionRiskParameters(String branchCode);

	

	

}
