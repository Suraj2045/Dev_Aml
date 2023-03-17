package com.nellinfotech.aml.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.dto.BankMstdetailsDTO;
import com.nellinfotech.aml.dto.BankParamDTO;
import com.nellinfotech.aml.dto.BranchPramDTO;
import com.nellinfotech.aml.dto.Request;
import com.nellinfotech.aml.entities.BanMaterialMst;
import com.nellinfotech.aml.entities.BanMerchantMst;
import com.nellinfotech.aml.entities.BankMst;
import com.nellinfotech.aml.entities.BankParam;
import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.BranchMst;
import com.nellinfotech.aml.entities.DeDupConfig;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.entities.TravelLogMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.BanMaterialRepository;
import com.nellinfotech.aml.repository.BanMerchantMstRepository;
import com.nellinfotech.aml.repository.Bankrepository;
import com.nellinfotech.aml.repository.DeDupConfigRepository;
import com.nellinfotech.aml.repository.HolidayMstRepository;
import com.nellinfotech.aml.repository.TravelLogMstRepository;
import com.nellinfotech.aml.service.BankService;
import com.nellinfotech.aml.service.HolidayService;
import com.nellinfotech.aml.repository.BranchHolidayRepository;

/**
 * @author Tushar
 */

@CrossOrigin(origins = "*")
@RestController

public class BankController {

	@Autowired
	private Bankrepository bankrepository;

	@Autowired
	private BankService bankService;

	@Autowired
	TravelLogMstRepository travelLogMstRepository;

	@Autowired
	BanMaterialRepository banMaterialRepository;

	@Autowired
	BanMerchantMstRepository banMerchantMstRepository;

	@Autowired
	DeDupConfigRepository deDupConfigRepository;
	
	@Autowired
	BranchHolidayRepository BranchHolidayRepository;

	Logger logger = LoggerFactory.getLogger(BankController.class);

	

	/**
	 * @author suraj
	 * @param bankCode
	 * @return
	 */
	
	// get bank list
	@GetMapping("/getbankdetail")
	public List<BankMstdetailsDTO> getbankdetail(@RequestParam String bankCode) {
		return bankService.getDetails(bankCode);
	}

	// get bank param
	@GetMapping("/getbankparam")
	public List<BankParamDTO> getbankparam(@RequestParam String bankCode) {
		return bankService.getParam(bankCode);
	}

	// get branch param
	@GetMapping("/getbranchparam")
	public List<BranchPramDTO> getbranchparam(@RequestParam String branchCode) {
		return bankService.getBranchParam(branchCode);
	}

	// delete bank
		@PostMapping("/deleteholiday")
		public String deleteHoliday(@RequestBody BranchHolidayMap BranchHolidayMap) {
			BranchHolidayRepository.deleteById(BranchHolidayMap.getId());
			return "holiday deleted";
		}
		
		// update bankMst
		@PostMapping("/updateBankMst")
		public BankMst updateBankMst(@RequestHeader HttpHeaders httpHeaders, @RequestBody BankMst BankMst) {
			try {
				Header header = HeaderValidator.headerConversion(httpHeaders);
				return bankService.updateBankMst(header, BankMst);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
		// update bankParam
		@PostMapping("/updateBankParam")
		public BankParam updateBankParam(@RequestHeader HttpHeaders httpHeaders, @RequestBody BankParam BankParam) {
			try {
				Header header = HeaderValidator.headerConversion(httpHeaders);
				return bankService.updateBankParam(header, BankParam);
			} catch (Exception e) {
				
			}
			return null;
		}
		
		//Get TravelLog By TravelLocation
		@PostMapping("/getTravelLogBytravelLocation")
		public List<TravelLogMst> getTravelLogBytravelLocation(@RequestParam String travelLocation,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
			 return travelLogMstRepository.getTravelLogByTravelLocation(travelLocation,fromDate,toDate);
			 }
	/**
	 * @author Tushar
		
	 * @param file
	 * @param bank_name
	 * @param bank_address
	 * @param bank_contactNo
	 * @param bank_ifsc
	 * @return
	 */
	// save bank details
	@RequestMapping(value = "/savebank", method = RequestMethod.POST, headers = "Content-type=multipart/form-data")
	public BankMst savebank(@RequestHeader HttpHeaders httpHeaders, @RequestParam(value = "file") MultipartFile file,
			@RequestParam String bank_name, @RequestParam String bank_address, @RequestParam Long bank_contactNo,
			@RequestParam String bank_ifsc, @RequestParam String bankCode) {
		try {

			Header header = HeaderValidator.headerConversion(httpHeaders);

			logger.info(" inside Bankrepository - savebank-" + bank_name);
			return bankService.savbank(header, file, bank_name, bank_address, bank_contactNo, bank_ifsc, bankCode);
		} catch (Exception e) {
			logger.error("savebank :-" + e);
		}
		return null;
	}

	/**
	 * @author Tushar
	 * @param branchParam
	 * @return
	 */
	// save bank list
	@PostMapping("/savebanklist")
	public String savebanklist(@RequestBody List<BankMst> bank) {
		bankrepository.saveAll(bank);
		return "all banks save ";
	}

	// update bank details
	@PostMapping("/updatebank")
	public String updatebank(@RequestBody BankMst bank) {
		bankrepository.updateBank(bank);
		return "all banks save ";
	}

	// get Bank By Id
	@GetMapping("/getBank")
	public Optional<BankMst> getbank(@RequestBody BankMst bank) {
		return bankrepository.findById(bank.getId());
	}

	// get Bank list
	@GetMapping("/getBankList")
	public List<BankMst> getBankList() {
		return bankrepository.findAll();
	}

	// delete bank
	@GetMapping("/deleteBank")
	public String deleteBank(@RequestBody BankMst bank) {
		bankrepository.deleteById(bank.getId());
		return "bank deleted";
	}

	
	/**
	 * @author Tushar
	 * @param bankParam return
	 */
	@PostMapping("/savebankparam")
	public BankParam saveBankParam(@RequestHeader HttpHeaders httpHeaders, @RequestBody BankParam bankParam) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);

			logger.info(" inside Bankrepository - saveBankParam-" + bankParam.getDefaultAmtFormat());
			return bankService.saveBankParam(header, bankParam);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@GetMapping("/getbankparamlist")
	public List<BankParam> getListBankParam() {
		try {
			return bankService.getListBankParam();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Tushar
	 * @param branchParam
	 * @return
	 */
	@PostMapping("/saveBranchMst")
	public BranchMst saveBranchMst(@RequestHeader HttpHeaders httpHeaders, @RequestBody BranchMst branchMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.saveBranchMst(header, branchMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Tushar
	 * @param httpHeaders
	 * @param branchMst
	 * @return
	 */
	@PostMapping("/updateBranchMst")
	public BranchMst updateBranchMst(@RequestHeader HttpHeaders httpHeaders, @RequestBody BranchMst branchMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.updateBranchMst(header, branchMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Tushar
	 * @param httpHeaders
	 * @param branchCode
	 * @return
	 */

	@GetMapping("/getBranchMastByCode")
	public BranchMst getBranchMastByCode(@RequestHeader HttpHeaders httpHeaders, @RequestParam String branchCode) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.getBranchMastByCode(branchCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Tushar
	 * @param httpHeaders
	 * @param bankCode
	 * @return
	 */
	@GetMapping("/getBranchByBankCode")
	public List<BranchMst> getBranchByBankCode(@RequestHeader HttpHeaders httpHeaders, @RequestParam String bankCode) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.getBranchByBankCode(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param travellog
	 * @return
	 */
	@PostMapping("/saveTravelLogMst")
	public TravelLogMst saveTravelLogMst(@RequestHeader HttpHeaders httpHeaders,
			@RequestBody TravelLogMst travelLogMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.saveTravelLogMst(header, travelLogMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param bankCode
	 * @return
	 */
	@GetMapping("/getTravelLogByBankCode")
	public List<TravelLogMst> getTravelLogByBankCode(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String bankCode) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.getTravelLogByBankCode(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param branchMst
	 * @return
	 */
	@PostMapping("/updateTravelLogMst")
	public TravelLogMst updateTravelLogMst(@RequestHeader HttpHeaders httpHeaders,
			@RequestBody TravelLogMst travelLogMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.updateTravelLogMst(header, travelLogMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// delete travel log
	@GetMapping("/deleteTravelLog")
	public String deleteTravelLog(@RequestBody TravelLogMst travelLogMst) {
		travelLogMstRepository.deleteById(travelLogMst.getId());
		return "travel log deleted";

	}

	/**
	 * @author Ravi
	 * @param travellog
	 * @return
	 */
	@PostMapping("/saveBanMaterialMst")
	public BanMaterialMst saveBanMaterialMst(@RequestHeader HttpHeaders httpHeaders,
			@RequestBody BanMaterialMst banMaterialMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.saveBanMaterialMst(header, banMaterialMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param bankCode
	 * @return
	 */
	@GetMapping("/getBanMaterialByBankCode")
	public List<BanMaterialMst> getBanMaterialByBankCode(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String bankCode) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.getBanMaterialByBankCode(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param branchMst
	 * @return
	 */
	@PostMapping("/updateBanMaterialMst")
	public BanMaterialMst updateBanMaterialMst(@RequestHeader HttpHeaders httpHeaders,
			@RequestBody BanMaterialMst banMaterialMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.updateBanMaterialMst(header, banMaterialMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// delete ban material
	@PostMapping("/deleteBanMaterialMst")
	public String deleteBanMaterialMst(@RequestBody BanMaterialMst banMaterialMst) {
		banMaterialRepository.deleteById(banMaterialMst.getId());
		return "ban material deleted";

	}

	/**
	 * @author Ravi
	 * @param travellog
	 * @return
	 */
	@PostMapping("/saveBanMerchantMst")
	public BanMerchantMst saveBanMerchantMst(@RequestHeader HttpHeaders httpHeaders,
			@RequestBody BanMerchantMst banMerchantMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.saveBanMerchantMst(header, banMerchantMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param bankCode
	 * @return
	 */
	@GetMapping("/getBanMerchantByBankCode")
	public List<BanMerchantMst> getBanMerchantByBankCode(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String bankCode) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.getBanMerchantByBankCode(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param branchMst
	 * @return
	 */
	@PostMapping("/updateBanMerchantMst")
	public BanMerchantMst updateBanMerchantMst(@RequestHeader HttpHeaders httpHeaders,
			@RequestBody BanMerchantMst banMerchantMst) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.updateBanMerchantMst(header, banMerchantMst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// delete ban merchant
	@GetMapping("/deleteBanMerchantMst")
	public String deleteBanMerchantMst(@RequestBody BanMerchantMst banMaterialMst) {
		banMerchantMstRepository.deleteById(banMaterialMst.getId());
		return "ban merchant deleted";

	}

	/**
	 * @author Ravi
	 * @param travellog
	 * @return
	 */
	@PostMapping("/saveDeDupConfig")
	public DeDupConfig saveDeDupConfig(@RequestHeader HttpHeaders httpHeaders, @RequestBody DeDupConfig deDupConfig) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.saveDeDupConfig(header, deDupConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param bankCode
	 * @return
	 */
	@GetMapping("/getDeDupByBankCode")
	public List<DeDupConfig> getDeDupByBankCode(@RequestHeader HttpHeaders httpHeaders, @RequestParam String bankCode) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.getDeDupByBankCode(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @author Ravi
	 * @param httpHeaders
	 * @param branchMst
	 * @return
	 */
	@PostMapping("/updateDeDup")
	public DeDupConfig updateDeDup(@RequestHeader HttpHeaders httpHeaders, @RequestBody DeDupConfig deDupConfig) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return bankService.updateDeDup(header, deDupConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// delete dedup
	@PostMapping("/deleteDeDup")
	public String deleteDeDup(@RequestBody BanMerchantMst banMaterialMst) {
		deDupConfigRepository.deleteById(banMaterialMst.getId());
		return "dedup deleted";

	}
}
