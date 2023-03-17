package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.entities.Monitor;
import com.nellinfotech.aml.entities.TravelLogMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.MonitorRepository;
import com.nellinfotech.aml.service.MonitorService;

@Service
public class MonitorServiceImpl implements MonitorService {

	@Autowired
	MonitorRepository monitorRepository;

    @Autowired
    DateValidator dateValidator;
    
    Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

	@Override
	public Monitor saveMonitoring(Header header, Monitor monitor) {
		  try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            monitor.setBankCode(header.getBankCode());
	            monitor.setCreatedBy(header.getUserId());
	            monitor.setCreatedDate(currentDate);
	            return monitorRepository.save(monitor);
	        } catch (Exception e) {
	            logger.error("saveMonitoring :" + e);
	        }
	        return null;
	}

	@Override
	public List<Monitor> getMonitoring(String bankCode) {
		   try {
	            return monitorRepository.getMonitoringByBankCode(bankCode);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

	@Override
	public Monitor updateMonitoring(Header header, Monitor monitor) {
		 try {
	            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
	            Monitor existingMonitor = monitorRepository.findById(monitor.getId()).orElse(null);
	            
	            existingMonitor.setReceivedOn(monitor.getReceivedOn());
	            existingMonitor.setTerminalId(monitor.getTerminalId());
	            existingMonitor.setMerchantName(monitor.getMerchantName());
	            existingMonitor.setCppDate(monitor.getCppDate());
	            existingMonitor.setMerchantLocation(monitor.getMerchantLocation());
	            existingMonitor.setIdentifiedDate(monitor.getIdentifiedDate());
	            existingMonitor.setMcc(monitor.getMcc());
	            existingMonitor.setFraudLocation(monitor.getFraudLocation());
	            existingMonitor.setAcquirerBank(monitor.getAcquirerBank());
	            existingMonitor.setDetectionSource(monitor.getDetectionSource());
	            existingMonitor.setMerchantIdNo(monitor.getMerchantIdNo());
	            existingMonitor.setIpAddress(monitor.getIpAddress());
	            
	            return monitorRepository.save(existingMonitor);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	}

}
