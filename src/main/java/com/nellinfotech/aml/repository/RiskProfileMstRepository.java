package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.entities.RiskProfileStatus;

public interface RiskProfileMstRepository extends JpaRepository<RiskProfileMst, Long> {

	@Query(value = " SELECT * FROM riskprofile_mst  WHERE branch_code=:branchCode and is_active=1 and riskprofile_mst.risk_code='KYC' " + 
			"and (riskprofile_mst.risk_parameter='Country of Origin' and riskprofile_mst.risk_value=:countryOfOrigin) "+
			"|| (riskprofile_mst.risk_parameter='Customer Type' and riskprofile_mst.risk_value=:customerType) " + 
			"|| (riskprofile_mst.risk_parameter='Industry' and riskprofile_mst.risk_value=:industry) "+
			"|| (riskprofile_mst.risk_parameter='Nationality' and riskprofile_mst.risk_value=:nationality) "+
			"|| (riskprofile_mst.risk_parameter='Qualification' and riskprofile_mst.risk_value=:qualification) "+
			"|| (riskprofile_mst.risk_parameter='Age Group' and riskprofile_mst.risk_value='20-25yrs') "+
			"|| (riskprofile_mst.risk_parameter='Occupation' and riskprofile_mst.risk_value=:occupation) "+
			"|| (riskprofile_mst.risk_parameter='Income Source' and riskprofile_mst.risk_value=:incomeSource) "+
			"|| (riskprofile_mst.risk_parameter='Is PEP' and riskprofile_mst.risk_value=:isPEP) ", nativeQuery = true)
	List<RiskProfileMst> getKYCParameters(String branchCode, String countryOfOrigin, String customerType, String industry, String nationality, String occupation, String qualification, String incomeSource, String isPEP);


	@Query(value = " SELECT * FROM riskprofile_mst  WHERE branch_code=:branchCode and is_active=1 and riskprofile_mst.risk_code='Transaction Type' ",nativeQuery = true)
	List<RiskProfileMst> getTransactionRiskParameters(String branchCode);

	@Query(value = " SELECT * FROM riskprofile_mst  WHERE branch_code=:branchCode and is_active=1", nativeQuery = true)
	List<RiskProfileMst> getKYCParametersByBranchCust(String branchCode);

	

	

}
