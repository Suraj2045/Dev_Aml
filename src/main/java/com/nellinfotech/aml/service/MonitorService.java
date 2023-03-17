package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.Monitor;
import com.nellinfotech.aml.model.Header;

public interface MonitorService {

	Monitor saveMonitoring(Header header, Monitor monitor);

	List<Monitor> getMonitoring(String bankCode);

	Monitor updateMonitoring(Header header, Monitor monitor);

}
