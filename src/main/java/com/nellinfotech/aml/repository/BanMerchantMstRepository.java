package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.BanMerchantMst;

public interface BanMerchantMstRepository extends JpaRepository<BanMerchantMst, Long> {

	List<BanMerchantMst> getBanMerchantByBankCode(String bankCode);

}
