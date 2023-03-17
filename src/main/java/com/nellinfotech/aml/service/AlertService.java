package com.nellinfotech.aml.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.nellinfotech.aml.dto.AlertMonitorGraph;
import com.nellinfotech.aml.dto.AlertPerformance;
import com.nellinfotech.aml.dto.AlertResponse;
import com.nellinfotech.aml.dto.AlertTransaction;
import com.nellinfotech.aml.dto.BranchRisk;
import com.nellinfotech.aml.dto.InvestigatorPerformance;
import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.AlertSubTypeMst;
import com.nellinfotech.aml.entities.AlertTypeMst;
import com.nellinfotech.aml.model.Header;

public interface AlertService {

	List<Alert> searchAlert(Header header, Alert alert);
	List<Alert> getAlertStatastics(String bankCode, String interval);
	List<Alert> getAlertClassification(String bankCode, String interval);
	List<Alert> getAlertStatus(String bankCode, String toDate, String fromDate, String alertType, String alertStatus);
	List<AlertTransaction> getAlertViolationStatistics(String bankCode, String toDate, String fromDate);
	List<Alert> getChannelWiseAlert(String bankCode, String toDate, String fromDate);
	List<AlertTypeMst> getAlertType(String bankCode);
	List<AlertSubTypeMst> getAlertSubType(String bankCode);
	String assignAlert(String bankCode, String assignUser, Long alertId, String assignUserId);
	String rejectAlert(String bankCode, String remark, Long alertId);
	List<AlertResponse> getAlert(String bankCode, Long alertId);
	List<Alert> getAlertAuditByUser(String bankCode,String toDate,String fromDate);
	List<Alert> getActivityAudit(String bankCode, String activity, String toDate, String fromDate);
	List<Alert> getAllAlertStatus(String bankCode);
	List<Alert> getAlertMonitor(String bankCode, String category, String type);
	List<AlertMonitorGraph> alertMonitorGraph(String bankCode, String category, String searchPeriod);
	List<AlertPerformance> alertPerformance(String bankCode, String searchPeriod);
	List<InvestigatorPerformance> caseInvestigatorPerformance(String bankCode, String searchPeriod);
	List<BranchRisk> branchRiskMonitor(String bankCode, String alertCode, String alertSubTypeCode, String timeDiff);
	ResponseEntity<Map<String, Object>> riskHeatMap(String bankCode, String alertCode, String timeDiff);
	ResponseEntity<Map<String, Object>> senarioSimulation(String bankCode, Date date1, Date date2, Long ruleId);
}
