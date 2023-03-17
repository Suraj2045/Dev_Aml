package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.entities.BanMaterialMst;
import com.nellinfotech.aml.entities.BranchParam;
import com.nellinfotech.aml.entities.NoiseWord;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.BranchRepository;
import com.nellinfotech.aml.repository.NoiseWordRepository;
import com.nellinfotech.aml.service.BranchService;

/**
 * @author Tushar
 */

@Service
public class BranchServiceImpl implements BranchService {
    
    @Autowired
    BranchRepository branchRepository;
    
    @Autowired
    NoiseWordRepository noiseWordRepository;
    
    Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);
    
    @Autowired
    DateValidator dateValidator;
    
    /**
     * @author Tushar
     * @param branchParam
     * @return
     */
    @Override
    public BranchParam saveBranch(Header header, BranchParam branchParam) {
        try {
            logger.info(" inside  BankServiceImpl  -savebank" + branchParam.getDefaultDateFormat());
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            branchParam.setBankCode(header.getBankCode());
            branchParam.setCreatedBy(header.getUserId());
            branchParam.setCreatedDate(currentDate);
            return branchRepository.save(branchParam);
        } catch (Exception e) {
            logger.error("savbank :" + e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @param branchParam
     * @return
     */
    @Override
    public BranchParam updateBranch(Header header, BranchParam branchParam) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            branchParam.setBankCode(header.getBankCode());
            branchParam.setModifiedBy(header.getUserId());
            branchParam.setModifiedDate(currentDate);
            BranchParam existingBranch = branchRepository.findById(branchParam.getId()).orElse(null);
            existingBranch.setBranchCode(branchParam.getBranchCode());
            existingBranch.setDefaultDateFormat(branchParam.getDefaultDateFormat());
            existingBranch.setDeafultNumberFormat(branchParam.getDeafultNumberFormat());
            existingBranch.setDefaultAmtFormat(branchParam.getDefaultAmtFormat());
            existingBranch.setDeafultLanguage(branchParam.getDeafultLanguage());
            existingBranch.setDecimalSep(branchParam.getDecimalSep());
            existingBranch.setDigitSep(branchParam.getDigitSep());
            branchRepository.save(existingBranch);
        } catch (Exception e) {
            logger.error("savbank :" + e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @param branchParam
     * @return
     */
    @Override
    public List<BranchParam> getBranchList() {
        try {
            return branchRepository.findAll();
            
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
    @Override
    public String deleteBranch(BranchParam branchParam) {
        try {
            BranchParam existingBranch = branchRepository.findById(branchParam.getId()).orElse(null);
            existingBranch.setIsActive(0);
            branchRepository.save(existingBranch);
            
            return "deletd successfully";
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

	@Override
	public NoiseWord saveNoiseWord(Header header, NoiseWord noiseWord) {
	try {
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            noiseWord.setCreatedBy(header.getUserId());
            noiseWord.setCreatedDate(currentDate);
            noiseWord.setBankCode(header.getBankCode());
            return noiseWordRepository.save(noiseWord);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
	}

	@Override
	public List<NoiseWord> getNoiseWordByBankCode(String bankCode) {
		   try {
	            return noiseWordRepository.getNoiseWordByBankCode(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public NoiseWord updateNoiseWord(Header header, NoiseWord noiseWord) {
		 try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            NoiseWord existingNoiseWord = noiseWordRepository.findById(noiseWord.getId()).orElse(null);
	            noiseWord.setNoiseword(noiseWord.getNoiseword());
	            return noiseWordRepository.save(noiseWord);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}
    
}
