package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.DueDiligenceMst;;

public interface DuediligenceMstRepository extends JpaRepository<DueDiligenceMst, Long> {

	@Query(value = " SELECT * FROM duediligence_mst  WHERE bank_code=:bankhCode and is_active=1", nativeQuery = true)
	List<DueDiligenceMst> getDueDiligencelist(String bankhCode);

//	@Query(value = " SELECT * FROM customer c  WHERE (c.customer_aadhaar='' || c.customer_aadhaar IS NULL) || (c.customer_fname='' or c.customer_fname IS NULL) || "
//			+ "(c.customer_gender='' || c.customer_gender IS NULL) || (c.mobile_no='' or mobile_no IS NULL) || (c.customer_pan='' || c.customer_pan IS NULL) || "
//			+ "(c.customer_monthly_income='' || c.customer_monthly_income IS NULL)", nativeQuery = true)
//	List<Customer> getNonComplientRecords();

	

	

}
