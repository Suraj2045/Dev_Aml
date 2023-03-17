package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.DeDupConfig;

public interface DeDupConfigRepository  extends JpaRepository<DeDupConfig, Long>{

	List<DeDupConfig> getDeDupByBankCode(String bankCode);

}
