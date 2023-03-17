package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.BranchHolidayRepository;
import com.nellinfotech.aml.repository.HolidayMstRepository;
import com.nellinfotech.aml.service.HolidayService;

/**
 * @author TUSHAR
 */

@Service
public class HolidayServiceImpl implements HolidayService {
    
    @Autowired
    HolidayMstRepository holidayRpository;
    
    @Autowired
    BranchHolidayRepository branchHolidayRpository;
    
    @Autowired
    DateValidator dateValidator;
    
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    
    @Override
    public HolidayMst saveHolidayMaster(Header header, HolidayMst holidayMst) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            holidayMst.setBankCode(header.getBankCode());
            holidayMst.setCreatedBy(header.getUserId());
            holidayMst.setCreatedDate(currentDate);
            return holidayRpository.save(holidayMst);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param branchHolidayMap
     *            return
     */
    @Override
    public BranchHolidayMap saveBranchHoliday(Header header, BranchHolidayMap branchHolidayMap) {
        
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            branchHolidayMap.setCreatedBy(header.getUserId());
            branchHolidayMap.setCreatedDate(currentDate);
            branchHolidayMap.setBankCode(header.getBankCode());
            return branchHolidayRpository.save(branchHolidayMap);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param brCode
     *            return
     */
    @Override
    public List<BranchHolidayMap> getBranchHollidayList(String brCode) {
        try {
            Integer isactive = 1;
            return branchHolidayRpository.findBybrCode(brCode, isactive);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param branchCode
     *            return
     */
    @Override
    public HolidayMst getHolidaylist(String branchCode) {
        try {
            return holidayRpository.findByBranchCode(branchCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param holidayMst
     *            return
     */
    @Override
    public HolidayMst updateHolidayMaster(Header header, HolidayMst holidayMst) {
        try {
            HolidayMst existingHolidayMst = holidayRpository.findById(holidayMst.getId()).orElse(null);
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            existingHolidayMst.setModifiedBy(header.getUserId());
            existingHolidayMst.setModifiedDate(currentDate);
            existingHolidayMst.setBranchCode(holidayMst.getBankCode());
            existingHolidayMst.setHolidayCode(holidayMst.getHolidayCode());
            existingHolidayMst.setHolidayDesc(holidayMst.getHolidayDesc());
            existingHolidayMst.setHolidayDate(holidayMst.getHolidayDate());
            existingHolidayMst.setBranchCode(holidayMst.getBranchCode());
            existingHolidayMst.setBankCode(holidayMst.getBankCode());
            return holidayRpository.save(existingHolidayMst);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param id
     *            return
     */
    @Override
    public String deleteHolidayMst(Long id) {
        try {
            HolidayMst existingHolidayMst = holidayRpository.findById(id).orElse(null);
            existingHolidayMst.setIsActive(1);
            holidayRpository.save(existingHolidayMst);
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

	
}
