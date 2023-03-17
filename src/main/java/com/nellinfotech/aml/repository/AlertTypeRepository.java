package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.AlertTypeMst;

public interface AlertTypeRepository extends JpaRepository<AlertTypeMst, Long>{

	@Query(value = " SELECT * FROM alert_type_mst WHERE bank_code =:bankCode", nativeQuery = true)
	List<AlertTypeMst> getAlertType(String bankCode);

}
