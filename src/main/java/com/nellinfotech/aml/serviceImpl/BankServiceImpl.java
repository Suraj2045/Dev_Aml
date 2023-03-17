package com.nellinfotech.aml.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.dto.BankMstdetailsDTO;
import com.nellinfotech.aml.dto.BankParamDTO;
import com.nellinfotech.aml.dto.BranchPramDTO;
import com.nellinfotech.aml.dto.Request;
import com.nellinfotech.aml.dto.TemplateDTO;
import com.nellinfotech.aml.entities.BanMaterialMst;
import com.nellinfotech.aml.entities.BanMerchantMst;
import com.nellinfotech.aml.entities.BankMst;
import com.nellinfotech.aml.entities.BankParam;
import com.nellinfotech.aml.entities.BranchMst;
import com.nellinfotech.aml.entities.DeDupConfig;
import com.nellinfotech.aml.entities.TravelLogMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.BanMaterialRepository;
import com.nellinfotech.aml.repository.BanMerchantMstRepository;
import com.nellinfotech.aml.repository.BankParamRepository;
import com.nellinfotech.aml.repository.Bankrepository;
import com.nellinfotech.aml.repository.BranchMstRepository;
import com.nellinfotech.aml.repository.BranchRepository;
import com.nellinfotech.aml.repository.DeDupConfigRepository;
import com.nellinfotech.aml.repository.TravelLogMstRepository;
import com.nellinfotech.aml.service.BankService;

/**
 * @author Tushar
 */

@Service
public class BankServiceImpl implements BankService {
    
    @Autowired
    Bankrepository bankRepo;
    @Autowired
    BranchRepository branchRepo;
    @Autowired
    private Environment env;
    @Autowired
    BankParamRepository bankparamRepository;
    
    @Autowired
    BranchMstRepository branchMstRepository;
    
    @Autowired
    TravelLogMstRepository travelLogMstRepository;
    
    @Autowired
    BanMaterialRepository banMaterialRepository;
    
    @Autowired
    BanMerchantMstRepository banMerchantMstRepository;
    
    @Autowired
    DeDupConfigRepository deDupConfigRepository;
    
    @Autowired
    DateValidator dateValidator;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
    
    @Autowired
    BankParamRepository BankParamRepository;
    /**
     * @author Tushar
     * @param file
     * @param bank_name
     * @param bank_address
     * @param bank_contactNo
     * @param bank_ifsc
     * @return
     */
    
    @Override
    public BankMst savbank(Header header, MultipartFile file, String bank_name, String bank_address,
            Long bank_contactNo, String bank_ifsc, String bankCode) {
        BankMst bank = new BankMst();
        logger.info(" inside  BankServiceImpl  -savebank" + bank_name);
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                
                File uploadFile = new File(fileName);
                String rootPath = env.getProperty("banklogo");
                File dir = new File(rootPath + File.separator + " ");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File serverFile = new File(dir.getAbsolutePath() + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                bank.setBankLogo(serverFile.toString());
            }
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            bank.setBankCode(bankCode);
            bank.setBankDefLang(header.getDefaultLang());
            bank.setCreatedBy(header.getUserId());
            bank.setCreatedDate(currentDate);
            bank.setBankDefLang(header.getDefaultLang());
            bank.setBankName(bank_name);
            bank.setBankAddress(bank_address);
            bank.setBankContactNo(bank_contactNo);
            bank.setBankIfsc(bank_ifsc);
            return bankRepo.save(bank);
            
        } catch (Exception e) {
            logger.error("savbank :" + e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @param bankParam
     * @return
     */
    @Override
    public BankParam saveBankParam(Header header, BankParam bankParam) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            bankParam.setCreatedBy(header.getUserId());
            bankParam.setCreatedDate(currentDate);
            bankParam.setBankCode(header.getBankCode());
            return bankparamRepository.save(bankParam);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<BankParam> getListBankParam() {
        try {
            return bankparamRepository.findAll();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public BranchMst saveBranchMst(Header header, BranchMst branchMst) {
        try {
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            branchMst.setCreatedBy(header.getUserId());
            branchMst.setCreatedDate(currentDate);
            branchMst.setBankCode(header.getBankCode());
            return branchMstRepository.save(branchMst);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public BranchMst updateBranchMst(Header header, BranchMst branchMst) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            BranchMst existingBranchMst = branchMstRepository.findById(branchMst.getId()).orElse(null);
            existingBranchMst.setBranchAddress(branchMst.getBranchAddress());
            existingBranchMst.setBranchCode(branchMst.getBranchCode());
            existingBranchMst.setBranchName(branchMst.getBranchName());
            existingBranchMst.setBranchType(branchMst.getBranchType());
            existingBranchMst.setBranchManager(branchMst.getBranchManager());
            existingBranchMst.setBranchCountry(branchMst.getBranchCountry());
            existingBranchMst.setBranchState(branchMst.getBranchState());
            existingBranchMst.setBranchCity(branchMst.getBranchCity());
            existingBranchMst.setMobile(branchMst.getMobile());
            existingBranchMst.setTele1(branchMst.getTele1());
            existingBranchMst.setTele2(branchMst.getTele2());
            existingBranchMst.setEmailId(branchMst.getEmailId());
            existingBranchMst.setModifiedBy(header.getUserId());
            existingBranchMst.setModifiedDate(currentDate);
            return branchMstRepository.save(existingBranchMst);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public BranchMst getBranchMastByCode(String branchCode) {
        try {
            return branchMstRepository.findBybranchCode(branchCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<BranchMst> getBranchByBankCode(String bankCode) {
        try {
            return branchMstRepository.findBybankCode(bankCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

	@Override
	public TravelLogMst saveTravelLogMst(Header header, TravelLogMst request) {
		  try {
	            
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            request.setCreatedBy(header.getUserId());
	            request.setCreatedDate(currentDate);
	            request.setBankCode(header.getBankCode());
	            return travelLogMstRepository.save(request);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public List<TravelLogMst> getTravelLogByBankCode(String bankCode) {
		   try {
	            return travelLogMstRepository.getTravelLogByBankCode(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public TravelLogMst updateTravelLogMst(Header header, TravelLogMst request) {
		  try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            TravelLogMst existingTravelMst = travelLogMstRepository.findById(request.getId()).orElse(null);
	            
	            existingTravelMst.setTravelDateFrom(request.getTravelDateFrom());
	            existingTravelMst.setTravelDateTo(request.getTravelDateTo());
	            existingTravelMst.setCustomerNo(request.getCustomerNo());
	            existingTravelMst.setCustomerName(request.getCustomerName());
	            existingTravelMst.setPrimaryCardholderName(request.getPrimaryCardholderName());
	            existingTravelMst.setPrimaryCardholderNo(request.getPrimaryCardholderNo());
	            existingTravelMst.setTravelLocation(request.getTravelLocation());
	            existingTravelMst.setTravelPurpose(request.getTravelPurpose());
	            existingTravelMst.setSpecialInstructions(request.getSpecialInstructions());
	            existingTravelMst.setLimitTransaction(request.getLimitTransaction());
	            
	            return travelLogMstRepository.save(existingTravelMst);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public BanMaterialMst saveBanMaterialMst(Header header, BanMaterialMst banMaterialMst) {
		try {
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            banMaterialMst.setCreatedBy(header.getUserId());
            banMaterialMst.setCreatedDate(currentDate);
            banMaterialMst.setBankCode(header.getBankCode());
            return banMaterialRepository.save(banMaterialMst);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
	}

	@Override
	public List<BanMaterialMst> getBanMaterialByBankCode(String bankCode) {
		   try {
	            return banMaterialRepository.getBanMaterialByBankCode(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public BanMaterialMst updateBanMaterialMst(Header header, BanMaterialMst banMaterialMst) {
		 try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            BanMaterialMst existingBanMaterialMst = banMaterialRepository.findById(banMaterialMst.getId()).orElse(null);
	            existingBanMaterialMst.setMaterialname(banMaterialMst.getMaterialname());
	            existingBanMaterialMst.setMaterialcode(banMaterialMst.getMaterialcode());
	            existingBanMaterialMst.setModifiedDate(currentDate);
	            existingBanMaterialMst.setFromdate(banMaterialMst.getFromdate());
	            existingBanMaterialMst.setTilldate(banMaterialMst.getTilldate());
	            return banMaterialRepository.save(existingBanMaterialMst);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public BanMerchantMst saveBanMerchantMst(Header header, BanMerchantMst banMerchantMst) {
	try {
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            banMerchantMst.setCreatedBy(header.getUserId());
            banMerchantMst.setCreatedDate(currentDate);
            banMerchantMst.setBankCode(header.getBankCode());
            return banMerchantMstRepository.save(banMerchantMst);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
	}

	@Override
	public List<BanMerchantMst> getBanMerchantByBankCode(String bankCode) {
		 try {
	            return banMerchantMstRepository.getBanMerchantByBankCode(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public BanMerchantMst updateBanMerchantMst(Header header, BanMerchantMst banMerchantMst) {
		 try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            BanMerchantMst existingBanMerchantMst = banMerchantMstRepository.findById(banMerchantMst.getId()).orElse(null);
	            existingBanMerchantMst.setMerchantscode(banMerchantMst.getMerchantscode());
	            existingBanMerchantMst.setMerhantname(banMerchantMst.getMerhantname());
	            existingBanMerchantMst.setModifiedDate(currentDate);
	            existingBanMerchantMst.setStatus(banMerchantMst.getStatus());
	            existingBanMerchantMst.setBanreason(banMerchantMst.getBanreason());
	            return banMerchantMstRepository.save(existingBanMerchantMst);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public DeDupConfig saveDeDupConfig(Header header, DeDupConfig deDupConfig) {
	try {
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            deDupConfig.setCreatedBy(header.getUserId());
            deDupConfig.setCreatedDate(currentDate);
            deDupConfig.setBankCode(header.getBankCode());
            return deDupConfigRepository.save(deDupConfig);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
	}

	@Override
	public List<DeDupConfig> getDeDupByBankCode(String bankCode) {
		   try {
	            return deDupConfigRepository.getDeDupByBankCode(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public DeDupConfig updateDeDup(Header header, DeDupConfig deDupConfig) {
		 try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            DeDupConfig existingDeDup = deDupConfigRepository.findById(deDupConfig.getId()).orElse(null);
	            existingDeDup.setCustEntity(deDupConfig.getCustEntity());
	            existingDeDup.setCustField(deDupConfig.getCustField());
	            existingDeDup.setModifiedDate(currentDate);
	 
	            return deDupConfigRepository.save(existingDeDup);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public List<BankMstdetailsDTO> getDetails(String bankCode) {
		
		List<BankMstdetailsDTO> obj=new ArrayList<BankMstdetailsDTO>();
		try
		{	
		obj=jdbcTemplate.query("select bank_master.id as id,bank_master.bank_code as bankCode,bank_master.bank_name as bank_name,bank_master.bank_logo as bankLogo,bank_master.bank_ifsc as bank_ifsc,bank_master.bank_address as bank_address,bank_master.bank_contact_no as bank_contactNo from bank_master where bank_code=?",
				new Object[] { bankCode },new BeanPropertyRowMapper<BankMstdetailsDTO>(BankMstdetailsDTO.class));	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<BankParamDTO> getParam(String bankCode) {
		List<BankParamDTO> obj=new ArrayList<BankParamDTO>();
		try
		{	
		obj=jdbcTemplate.query("select bank_param.id as id, bank_param.dafault_date_format as defaultDateFormat, bank_param.dafault_amount_format as defaultAmtFormat,bank_param.decimal_seprator as decimalSep,bank_param.digit_seprator as digitSep,bank_param.dafault_currency as defaultCurrency,bank_param.dafault_language as defaultLanguage,bank_param.dafault_home as defaultHome,bank_param.customer_days as customerDays from bank_param where bank_code=?",
				new Object[] { bankCode },new BeanPropertyRowMapper<BankParamDTO>(BankParamDTO.class));	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<BranchPramDTO> getBranchParam(String branchCode) {
		
		List<BranchPramDTO> obj=new ArrayList<BranchPramDTO>();
		try
		{	
		obj=jdbcTemplate.query("select \r\n"
				 +"branch_param.id as id, \r\n"
				+ "branch_param.deafult_date_format as deafult_date_format, \r\n"
				+ "branch_param.deafult_amount_format as deafult_amount_format,\r\n"
				+ "branch_param.decimal_seprator as decimal_seprator,\r\n"
				+ "branch_param.digitl_seprator as digit_seprator,\r\n"
				+ "branch_param.deafult_language as deafult_language,\r\n"
				+ "branch_param.branch_code as branch_code,\r\n"
				+ "branch_param.deafult_number_format as deafult_number_format\r\n"
				+ "from branch_param \r\n"
				+ "where branch_code=?",
				new Object[] { branchCode },new BeanPropertyRowMapper<BranchPramDTO>(BranchPramDTO.class));	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
    
	

	@Override
	public BankMst updateBankMst(Header header, BankMst newBankMst) {
		 try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            BankMst BankMst = bankRepo.findById(newBankMst.getId()).orElse(null);
	            BankMst.setBankName(newBankMst.getBankName());
	            BankMst.setBankLogo(newBankMst.getBankLogo());
	            BankMst.setBankDefLang(newBankMst.getBankDefLang());
	            BankMst.setBankIfsc(newBankMst.getBankIfsc());
	            BankMst.setBankAddress(newBankMst.getBankAddress());
	            BankMst.setBankContactNo(newBankMst.getBankContactNo());
	          
	            return bankRepo.save(BankMst);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public BankParam updateBankParam(Header header, BankParam newbankParam) {
		try {
		 Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
		 BankParam BankParam = BankParamRepository.findById(newbankParam.getId()).orElse(null);
		 BankParam.setDefaultDateFormat(newbankParam.getDefaultDateFormat());
		 BankParam.setDefaultAmtFormat(newbankParam.getDefaultAmtFormat());
		 BankParam.setDecimalSep(newbankParam.getDecimalSep());
		 BankParam.setDigitSep(newbankParam.getDigitSep());
		 BankParam.setDefaultCurrency(newbankParam.getDefaultCurrency());
		 BankParam.setDefaultLanguage(newbankParam.getDefaultLanguage());
		 BankParam.setDefaultHome(newbankParam.getDefaultHome());
		 BankParam.setCustomerDays(newbankParam.getCustomerDays());
		 BankParam.setDefaultLanguage(newbankParam.getDefaultLanguage());
		 
       
         return BankParamRepository.save(BankParam);
     } catch (Exception e) {
        
     }
     return null;
	}

	
	
}
