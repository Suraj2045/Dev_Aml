package com.nellinfotech.aml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.BranchParam;
import com.nellinfotech.aml.entities.NoiseWord;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.NoiseWordRepository;
import com.nellinfotech.aml.service.BranchService;

/**
 * @author Tushar
 */
@CrossOrigin(origins = "*")
@RestController
public class BranchController {
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    NoiseWordRepository noiseWordRepository;
    
    Logger logger = LoggerFactory.getLogger(BankController.class);
    
    /**
     * @author Tushar
     * @param branchParam
     * @return
     */
    
    @PostMapping("/savebranch")
    public BranchParam saveBranch(@RequestHeader HttpHeaders httpHeaders, @RequestBody BranchParam branchParam) {
        try {
            logger.info("inside BranchController: --saveBranch" + branchParam.getDefaultAmtFormat());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return branchService.saveBranch(header, branchParam);
        } catch (Exception e) {
            logger.error("BranchController: --saveBranch" + e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @param branchParam
     * @return
     */
    @PostMapping("/updatebranch")
    public BranchParam updateBranch(@RequestHeader HttpHeaders httpHeaders, @RequestBody BranchParam branchParam) {
        try {
            logger.info("inside BranchController: --updateBranch" + branchParam.getDefaultAmtFormat());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return branchService.updateBranch(header, branchParam);
            
        } catch (Exception e) {
            logger.error("BranchController: --updateBranch" + e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @param branchParam
     * @apiNote get branch list
     * @return
     */
    
    @GetMapping("/getbranchlist")
    public List<BranchParam> getBranchList() {
        try {
            logger.info("inside BranchController: --getBranchList");
            return branchService.getBranchList();
        } catch (Exception e) {
            logger.error("BranchController: --getBranchList" + e);
        }
        return null;
    }
    
    /**
     * @author Tushar
     * @param branchParam
     * @apiNote delete branch update del flag
     * @return
     */
    @PostMapping("/deletebranch")
    public String deleteBranch(@RequestBody BranchParam branchParam) {
        try {
            logger.info("inside BranchController: --deletebranch");
            return branchService.deleteBranch(branchParam);
        } catch (Exception e) {
            logger.error("BranchController: --deleteBranch" + e);
        }
        return null;
    }
    
    
    /**
     * @author Ravi
     * @param travellog
     * @return
     */
    @PostMapping("/saveNoiseWord")
    public NoiseWord saveTravelLogMst(@RequestHeader HttpHeaders httpHeaders, @RequestBody NoiseWord noiseWord) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return branchService.saveNoiseWord(header, noiseWord);
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
    @GetMapping("/getNoiseWordByBankCode")
    public List<NoiseWord> getNoiseWordByBankCode(@RequestHeader HttpHeaders httpHeaders, @RequestParam String bankCode) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return branchService.getNoiseWordByBankCode(bankCode);
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
    @PostMapping("/updateNoiseWord")
    public NoiseWord updateNoiseWord(@RequestHeader HttpHeaders httpHeaders, @RequestBody NoiseWord noiseWord) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return branchService.updateNoiseWord(header, noiseWord);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    //delete noise
    @GetMapping("/deleteNoiseWord")
    public String deleteTravelLog(@RequestBody NoiseWord noiseWord) {
    	noiseWordRepository.deleteById(noiseWord.getId());
        return "noise word deleted";
        
    }
    
    
}
