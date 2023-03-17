package com.nellinfotech.aml.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nellinfotech.aml.dto.RiskProfile;
import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.RiskProfileMst;
import com.nellinfotech.aml.entities.RoleMst;
import com.nellinfotech.aml.entities.Transaction;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByCustCode(String custCode);
	
	@Query(value = " SELECT * FROM customer WHERE is_active=1 and (customer_type like %:customerType% || cust_code like %:custCode% || customer_name like %:customerName%	"
			+ "|| bank_code like %:bankCode% || registration_country like %:countryOfReg% || customer_cate like %:customerCategory% || national_id_1 like %:nationalId1%"
			+ "|| addr1 like %:addr1% || addr_cty like %:city% || addr_postalcode like %:postalCode% || mobile_no like %:mobileNo% || customer_risk_manual like %:riskManual%)", nativeQuery = true)
	public List<Customer> findByCustomerParam(String customerType, String custCode, String customerName, 
			String countryOfReg, String customerCategory, String nationalId1, String addr1, String city,
			String postalCode, String mobileNo, Double riskManual, String bankCode);
	
	
	@Query(value = " SELECT * " + 
					" FROM customer c WHERE c.is_active =1 and cust_code !=:custCode and (c.customer_fname like %:custFName% || c.customer_lname like %:custLName% || c.addr1 like %:addr1% || c.addr2 like %:addr2%" + 
					" || (c.customer_pan =:custPAN and c.customer_pan is NOT NULL) || (c.customer_aadhaar =:custAdhar and c.customer_aadhaar IS NOT NULL) "+
					" || (c.mobile_no =:custMobile and c.mobile_no is NOT NULL) || (c.customer_dob =:custDOB and c.customer_dob is NOT NULL))", nativeQuery = true)
	public List<Customer> findByDeDup(String custCode, String custFName, String custLName, String addr1, String addr2,
			 String custPAN, String custAdhar, String custMobile, String custDOB);
	
	@Transactional
	@Modifying
    @Query(value = " Update transaction t set t.cust_code=:parentCustCode where t.cust_code=:dedupCustCode", nativeQuery = true)
	public void runTransactionDedup(List<String> parentCustCode, List<String> dedupCustCode);

	@Transactional
	@Modifying
	@Query(value = " Update account a set a.cust_code=:parentCustCode where a.cust_code=:dedupCustCode", nativeQuery = true)
	public void runAccountDedup(List<String> parentCustCode, List<String> dedupCustCode);

	@Transactional
	@Modifying
	@Query(value = " Update customer c set c.is_active=2 where c.cust_code=:dedupCustCode", nativeQuery = true)
	void runCustomerDedup(List<String> dedupCustCode);
	
	@Query(value = " SELECT * FROM customer c  WHERE c.bank_code=:bankCode and c.is_active=1 and (c.customer_aadhaar='' || c.customer_aadhaar IS NULL) || (c.customer_fname='' or c.customer_fname IS NULL) || "
			+ "(c.customer_gender='' || c.customer_gender IS NULL) || (c.mobile_no='' or mobile_no IS NULL) || (c.customer_pan='' || c.customer_pan IS NULL) || "
			+ "(c.customer_monthly_income='' || c.customer_monthly_income IS NULL)", nativeQuery = true)
	List<Customer> getNonComplientRecords(String bankCode);

	@Query(value = " SELECT * FROM riskprofile_mst", nativeQuery = true)
	public List<RiskProfileMst> getKYCParameters(String branchCode);

	public List<Customer> findByBankCode(String bankCode);
	
	public List<Customer> findBybaseBrCode(String baseBrCode);

		
}

