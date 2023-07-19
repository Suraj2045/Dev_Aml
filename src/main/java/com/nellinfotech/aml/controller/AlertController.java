package com.nellinfotech.aml.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
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
import com.nellinfotech.aml.service.AlertService;

/**
 * @author Tushar
 */
@CrossOrigin(origins = "*")
@RestController
public class AlertController {
	@Autowired
	AlertService alertService;

	Logger logger = LoggerFactory.getLogger(BankController.class);

	/**
	 * @author Tushar
	 * @param branchParam
	 * @return
	 */

	@PostMapping("/serachAlert")
	public List<Alert> serachAlert(@RequestHeader HttpHeaders httpHeaders, @RequestBody Alert alert) {
		try {
			logger.info("inside AlertController: --serachAlert");
			Header header = HeaderValidator.headerConversion(httpHeaders);
			return alertService.searchAlert(header, alert);
		} catch (Exception e) {
			logger.error("AlertController: --serachAlert" + e);
		}
		return null;
	}

	@PostMapping("/getAlertStatastics")
	public List<Alert> getAlertStatastics(@RequestHeader HttpHeaders httpHeaders, @RequestParam String interval) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertStatastics(bankCode, interval);

		} catch (Exception e) {
			logger.error("Error in customer controller" + e);
		}
		return null;

	}

	@PostMapping("/getAlertClassification")
	public Page<Alert> getAlertClassification(@RequestHeader HttpHeaders httpHeaders, @RequestParam String interval,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			Pageable pageable = PageRequest.of(page, size);
			Page<Alert> alertPage = alertService.getAlertClassification(bankCode, interval, pageable);

			return alertPage;
		} catch (Exception e) {
			logger.error("Error in customer controller" + e);
		}
		return null;

	}

	@PostMapping("/getAlertStatus")
	public List<Alert> getAlertStatus(@RequestHeader HttpHeaders httpHeaders, @RequestParam String toDate,
			@RequestParam String fromDate, @RequestParam String alertType, @RequestParam String alertStatus) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertStatus(bankCode, toDate, fromDate, alertType, alertStatus);

		} catch (Exception e) {
			logger.error("Error in customer controller" + e);
		}
		return null;

	}

	@PostMapping("/getAlertViolationStatistics")
	public List<AlertTransaction> getAlertViolationStatistics(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String toDate, @RequestParam String fromDate) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertViolationStatistics(bankCode, toDate, fromDate);

		} catch (Exception e) {
			logger.error("AlertController: --getAlertViolationStatistics" + e);
		}
		return null;

	}

	@PostMapping("/getChannelWiseAlert")
	public List<Alert> getChannelWiseAlert(@RequestHeader HttpHeaders httpHeaders, @RequestParam String toDate,
			@RequestParam String fromDate) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getChannelWiseAlert(bankCode, toDate, fromDate);

		} catch (Exception e) {
			logger.error("AlertController: --getChannelWiseAlert" + e);
		}
		return null;

	}

	@GetMapping("/getAlertType")
	public List<AlertTypeMst> getAlertType(@RequestHeader HttpHeaders httpHeaders) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertType(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@GetMapping("/getAlertSubType")
	public List<AlertSubTypeMst> getAlertSubType(@RequestHeader HttpHeaders httpHeaders) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertSubType(bankCode);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@PostMapping("/assignAlert")
	public String assignAlert(@RequestHeader HttpHeaders httpHeaders, @RequestParam String assignUser,
			@RequestParam Long alertId, @RequestParam String assignUserId) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.assignAlert(bankCode, assignUser, alertId, assignUserId);

		} catch (Exception e) {
			logger.error("AlertController: --assignAlert" + e);
		}
		return null;
	}

	@PostMapping("/getAlertMonitor")
	public List<Alert> getAlertMonitor(@RequestHeader HttpHeaders httpHeaders, @RequestParam String category,
			@RequestParam String type) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertMonitor(bankCode, category, type);

		} catch (Exception e) {
			logger.error("Error in customer controller" + e);
		}
		return null;

	}

	@PostMapping("/rejectAlert")
	public String rejectAlert(@RequestHeader HttpHeaders httpHeaders, @RequestParam String remark,
			@RequestParam Long alertId) {
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.rejectAlert(bankCode, remark, alertId);

		} catch (Exception e) {
			logger.error("AlertController: --rejectAlert" + e);
		}
		return null;

	}

	@PostMapping("/getAlert")
	public List<AlertResponse> getAlert(@RequestHeader HttpHeaders httpHeaders, @RequestParam Long alertId) {
		try {

			logger.info(" inside AlertController - getAlert-");
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlert(bankCode, alertId);
		} catch (Exception e) {
			logger.error("Error AlertController - getAlert- " + e);
		}
		return null;
	}

	@PostMapping("/getAlertAuditByUser")
	public List<Alert> getAlertAuditByUser(@RequestHeader HttpHeaders httpHeaders, @RequestParam String toDate,
			@RequestParam String fromDate) {
		try {

			logger.info(" inside AlertController - getAlertAuditByUser-");
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAlertAuditByUser(bankCode, toDate, fromDate);
		} catch (Exception e) {
			logger.error("Error AlertController - getAlertAuditByUser- " + e);
		}
		return null;
	}

	@PostMapping("/getActivityAudit")
	public List<Alert> getActivityAuditByUser(@RequestHeader HttpHeaders httpHeaders, @RequestParam String activity,
			@RequestParam String toDate, @RequestParam String fromDate) {
		try {

			logger.info(" inside AlertController - getActivityAudit-");
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getActivityAudit(bankCode, activity, toDate, fromDate);
		} catch (Exception e) {
			logger.error("Error AlertController - getActivityAudit- " + e);
		}
		return null;
	}

	@PostMapping("/getAllAlertStatus")
	public List<Alert> getAllAlertStatus(@RequestHeader HttpHeaders httpHeaders) {
		try {

			logger.info(" inside AlertController - getAllAlertStatus-");
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.getAllAlertStatus(bankCode);
		} catch (Exception e) {
			logger.error("Error AlertController - getAllAlertStatus- " + e);
		}
		return null;
	}

	@GetMapping("/alertMonitorGraph")
	public List<AlertMonitorGraph> alertMonitorGraph(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String category, @RequestParam String searchPeriod) {
		logger.info("inside  alertMonitorGraph" + searchPeriod);
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.alertMonitorGraph(bankCode, category, searchPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/alertPerformance")
	public List<AlertPerformance> alertPerformance(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String searchPeriod) {
		logger.info("inside  alertPerformance" + searchPeriod);
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.alertPerformance(bankCode, searchPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/caseInvestigatorPerformance")
	public List<InvestigatorPerformance> caseInvestigatorPerformance(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String searchPeriod) {
		logger.info("inside  caseInvestigatorPerformance" + searchPeriod);
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.caseInvestigatorPerformance(bankCode, searchPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/branchRiskMonitor")
	public List<BranchRisk> branchRiskMonitor(@RequestHeader HttpHeaders httpHeaders, @RequestParam String alertCode,
			String alertSubTypeCode, String timeDiff) {
		logger.info("inside  caseInvestigatorPerformance" + alertCode);
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.branchRiskMonitor(bankCode, alertCode, alertSubTypeCode, timeDiff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/riskHeatMap")
	public ResponseEntity<Map<String, Object>> riskHeatMap(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String alertCode, String timeDiff) {
		logger.info("inside  caseInvestigatorPerformance" + alertCode);
		try {
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.riskHeatMap(bankCode, alertCode, timeDiff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/senarioSimulation")
	public ResponseEntity<Map<String, Object>> senarioSimulation(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam String startDate, String endDate, @RequestParam Long ruleId) {
		logger.info("inside  caseInvestigatorPerformance" + startDate);
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = df.parse(startDate);
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = df1.parse(endDate);
			Header header = HeaderValidator.headerConversion(httpHeaders);
			String bankCode = header.getBankCode();
			return alertService.senarioSimulation(bankCode, date1, date2, ruleId);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return (new ResponseEntity<Map<String, Object>>(HttpStatus.EXPECTATION_FAILED));
	}

}
