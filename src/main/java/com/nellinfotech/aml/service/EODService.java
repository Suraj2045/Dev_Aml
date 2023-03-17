package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.EODHistory;
import com.nellinfotech.aml.entities.EODMst;

public interface EODService {

	List<EODMst> getEODMasterConfiguration();

//	String updateEODConfiguration(List<EODMst> eodList);

	String updateNextRunDate(EODMst eodMst);

	EODHistory saveEODHistory(EODHistory history1);

	void runEODConfiguration(List<EODMst> eodList);

	int updateEODConfiguration(List<EODMst> eodList);
	
	

}
