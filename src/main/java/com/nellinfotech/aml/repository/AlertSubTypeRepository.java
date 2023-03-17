package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.AlertSubTypeMst;

public interface AlertSubTypeRepository extends JpaRepository<AlertSubTypeMst, Long>{

	@Query(value = " SELECT * FROM alertsubtype_mst WHERE bank_code =:bankCode", nativeQuery = true)
	List<AlertSubTypeMst> getAlertSubType(String bankCode);

}
