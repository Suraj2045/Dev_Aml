package com.nellinfotech.aml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.HolidayService;

/**
 * @author TUSHAR
 */
@CrossOrigin(origins = "*")
@RestController
public class Holidaycontroller {
    
    @Autowired
    private HolidayService holidayService;
    
    Logger logger = LoggerFactory.getLogger(BankController.class);
    
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    @PostMapping("/saveHolidayMaster")
    public HolidayMst saveHolidayMaster(@RequestHeader HttpHeaders httpHeaders, @RequestBody HolidayMst holidayMst) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return holidayService.saveHolidayMaster(header, holidayMst);
        } catch (Exception e) {
            logger.error("Holidaycontroller  - saveBranchHoliday" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    @PostMapping("/getHolidaylist")
    public HolidayMst getHolidaylist(@RequestBody HolidayMst holidayMst) {
        try {
            
            return holidayService.getHolidaylist(holidayMst.getBranchCode());
        } catch (Exception e) {
            logger.error("Holidaycontroller  - saveBranchHoliday" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    @PostMapping("/updateHolidayMaster")
    public HolidayMst updateHolidayMaster(@RequestHeader HttpHeaders httpHeaders, @RequestBody HolidayMst holidayMst) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return holidayService.updateHolidayMaster(header, holidayMst);
        } catch (Exception e) {
            logger.error("Holidaycontroller  - saveBranchHoliday" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    @PostMapping("/deleteHolidayMst")
    public String deleteHolidayMst(@RequestBody HolidayMst holidayMst) {
        try {
            
            return holidayService.deleteHolidayMst(holidayMst.getId());
        } catch (Exception e) {
            logger.error("Holidaycontroller  - saveBranchHoliday" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param branchHolidayMap
     *            return
     */
    @PostMapping("/saveBranchHoliday")
    public BranchHolidayMap saveBranchHoliday(@RequestHeader HttpHeaders httpHeaders,
            @RequestBody BranchHolidayMap branchHolidayMap) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return holidayService.saveBranchHoliday(header, branchHolidayMap);
        } catch (Exception e) {
            logger.error("Holidaycontroller  - saveBranchHoliday" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param branchHolidayMap
     *            return
     */
    @PostMapping("/getbranchholidaylist")
    public List<BranchHolidayMap> getBranchHollidayList(@RequestBody BranchHolidayMap branchHoloidayMap) {
        try {
            return holidayService.getBranchHollidayList(branchHoloidayMap.getBranchCode());
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
