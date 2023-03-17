package com.nellinfotech.aml.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.nellinfotech.aml.dto.BankMstdetailsDTO;
import com.nellinfotech.aml.dto.BankParamDTO;
import com.nellinfotech.aml.dto.BranchPramDTO;
import com.nellinfotech.aml.dto.Request;
import com.nellinfotech.aml.entities.BanMaterialMst;
import com.nellinfotech.aml.entities.BanMerchantMst;
import com.nellinfotech.aml.entities.BankMst;
import com.nellinfotech.aml.entities.BankParam;
import com.nellinfotech.aml.entities.BranchMst;
import com.nellinfotech.aml.entities.DeDupConfig;
import com.nellinfotech.aml.entities.TravelLogMst;
import com.nellinfotech.aml.model.Header;

public interface BankService {
	List<BankMstdetailsDTO> getDetails(String bankCode);
	
	List<BankParamDTO> getParam(String bankCode);
	
	List<BranchPramDTO> getBranchParam(String branchCode);
	
	BankMst  updateBankMst(Header header, BankMst BankMst);
	
	BankParam updateBankParam (Header header,BankParam bankParam);
	
    BankMst savbank(Header header, MultipartFile file, String bank_name, String bank_address, Long bank_contactNo,
            String bank_ifsc, String bankCode);
    
    BankParam saveBankParam(Header header, BankParam bankParam);
    
    List<BankParam> getListBankParam();
    
    BranchMst saveBranchMst(Header header, BranchMst branchMst);
    
    BranchMst updateBranchMst(Header header, BranchMst branchMst);
    
    BranchMst getBranchMastByCode(String branchCode);
    
    List<BranchMst> getBranchByBankCode(String bankCode);

	TravelLogMst saveTravelLogMst(Header header, TravelLogMst travelLogMst);

	List<TravelLogMst> getTravelLogByBankCode(String bankCode);

	TravelLogMst updateTravelLogMst(Header header, TravelLogMst travelLogMst);

	BanMaterialMst saveBanMaterialMst(Header header, BanMaterialMst banMaterialMst);

	List<BanMaterialMst> getBanMaterialByBankCode(String bankCode);

	BanMaterialMst updateBanMaterialMst(Header header, BanMaterialMst banMaterialMst);

	BanMerchantMst saveBanMerchantMst(Header header, BanMerchantMst banMerchantMst);

	List<BanMerchantMst> getBanMerchantByBankCode(String bankCode);

	BanMerchantMst updateBanMerchantMst(Header header, BanMerchantMst banMerchantMst);

	DeDupConfig saveDeDupConfig(Header header, DeDupConfig deDupConfig);

	List<DeDupConfig> getDeDupByBankCode(String bankCode);

	DeDupConfig updateDeDup(Header header, DeDupConfig deDupConfig);

    
}
