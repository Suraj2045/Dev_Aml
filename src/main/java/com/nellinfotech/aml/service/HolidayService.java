package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.model.Header;

public interface HolidayService {
    
    BranchHolidayMap saveBranchHoliday(Header header, BranchHolidayMap branchHolidayMap);
    
    List<BranchHolidayMap> getBranchHollidayList(String brCode);
    
    HolidayMst saveHolidayMaster(Header header, HolidayMst holidayMst);
    
    HolidayMst getHolidaylist(String branchCode);
    
    HolidayMst updateHolidayMaster(Header header, HolidayMst holidayMst);
    
    String deleteHolidayMst(Long id);
}
