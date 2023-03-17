package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.BanMaterialMst;

public interface BanMaterialRepository extends JpaRepository<BanMaterialMst, Long> {

	List<BanMaterialMst> getBanMaterialByBankCode(String bankCode);

}
