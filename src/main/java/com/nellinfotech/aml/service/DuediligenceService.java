package com.nellinfotech.aml.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.nellinfotech.aml.entities.BranchHolidayMap;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.model.Header;

public interface DuediligenceService {

	DueDiligenceMst saveDueDiligenceMaster(Header header, DueDiligenceMst dueDiligenceMst);
	
	List<DueDiligenceMst> getDueDiligencelist(String bankCode);

	DueDiligenceMst updateDueDiligenceMaster(Header header, DueDiligenceMst dueDiligenceMst);

	String deleteDueDiligenceMaster(Long id);

	ResponseEntity<Resource> runDueDiligenceJob(String bankCode);

	
    
    
    
}
