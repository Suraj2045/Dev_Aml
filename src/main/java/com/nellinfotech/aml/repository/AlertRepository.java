package com.nellinfotech.aml.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.dto.AlertPerformance;
import com.nellinfotech.aml.entities.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{

	@Query(value = " SELECT * FROM alert WHERE is_active=1 and bank_code =:bankCode or (created_date BETWEEN :createdDate and :createdDate) or (ALERT_CODE like %:alertCode% || ALERT_STATUS like %:alertStatus% || CUSTOMER_NAME like %:customerName%	"
			+ "|| CUST_ID like %:custId%  || RISK_SEVERITY like %:riskSeverity% || ASSIGNED_USER like %:assignedUser% || LOCATION like %:location% || ACCOUNT_NO like %:accountId% || ALERTSUBTYPE_CODE like %:alertSubTypeCode%)", nativeQuery = true)
	List<Alert> searchAlert(String alertCode, String alertStatus, String customerName, String custId, String riskSeverity, String assignedUser, String location, String accountId, Date createdDate, String alertSubTypeCode, String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE()) < created_date)", nativeQuery = true)
	List<Alert> getAlertStatastics(String bankCode);

	@Query(value = " SELECT * FROM alert  WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 WEEK) < created_date)", nativeQuery = true)
	List<Alert> getOneWeekAlertStatastics(String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < created_date)", nativeQuery = true)
	List<Alert> getOneMonthAlertStatastics(String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 QUARTER) < created_date)", nativeQuery = true)
	List<Alert> getOneQuarterAlertStatastics(String bankCode);
	
	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 6 MONTH) < created_date)", nativeQuery = true)
	List<Alert> getSixMonthAlertStatastics(String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 YEAR) < created_date)", nativeQuery = true)
	List<Alert> getOneYearAlertStatastics(String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode group by alert_status", nativeQuery = true)
	List<Alert> getAlertStatusbyGroup(String bankCode);
	
	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode", nativeQuery = true)
	List<Alert> getAlertNamebyGroup(String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (created_date BETWEEN :fromDate and :toDate) and alert_code=:alertType and alert_status=:alertStatus", nativeQuery = true)
	List<Alert> getAlertStatus(String bankCode, String toDate, String fromDate, String alertType, String alertStatus);

	@Query(value = " SELECT a.*, count(a.id) as id FROM alert a WHERE bank_code =:bankCode and (created_date BETWEEN :fromDate and :toDate) group by alert_code", nativeQuery = true)
	List<Alert> getAlertViolationStatistics(String bankCode, String toDate, String fromDate);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and alert_code=:alertCode", nativeQuery = true)
	List<Alert> getAlertByAlertCode(String alertCode, String bankCode);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (created_date BETWEEN :fromDate and :toDate) group by alert_code, channel", nativeQuery = true)
	List<Alert> getChannelWiseAlert(String bankCode, String toDate, String fromDate);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and channel=:chennel and alert_code=:alertCode and (created_date BETWEEN :fromDate and :toDate) ", nativeQuery = true)
	List<Alert> getAlertByChannel(String bankCode, String toDate, String fromDate, String chennel, String alertCode);
	
	@Transactional
	@Modifying
	@Query(value="Update alert set assigned_user=:assignUser, assignedUserId=:assignUserId where bank_code=:bankCode and id=:alertId", nativeQuery = true)
	Integer assignAlert(String bankCode, String assignUser, Long alertId, String assignUserId);

	@Transactional
	@Modifying
	@Query(value="Update alert set remark=:remark, alert_status='Reject' where bank_code=:bankCode and id=:alertId", nativeQuery = true)
	Integer rejectAlert(String bankCode, String remark, Long alertId);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and id=:alertId", nativeQuery = true)
	Alert getAlert(String bankCode, Long alertId);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and (created_date BETWEEN :fromDate and :toDate)", nativeQuery = true)
	List<Alert> getAlertAuditByUser(String bankCode, String toDate, String fromDate);

	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode and alert_status=:activity and (created_date BETWEEN :fromDate and :toDate)", nativeQuery = true)
	List<Alert> getActivityAudit(String bankCode, String activity, String toDate, String fromDate);

	@Query(value = " SELECT * from alert WHERE bank_code =:bankCode group by alert_status", nativeQuery = true)
	List<Alert> getAllAlertStatus(String bankCode);

	@Query(value = " SELECT a.id as id,a.auth_status as authStatus,a.bank_code as bankCode,a.created_by as createdBy,a.created_date as createdDate,a.is_active as isActive,a.modified_by as modifiedBy,a.modified_date as modifiedDate,a.version as version,a.alert_code as alertCode, "
			+ "a.alert_percent as alertPercent,a.alert_status as alertStatus,a.alertsubtype_code as alertSubTypeCode,a.assigned_userid as assignedUserId,a.branch_code as branchCode,a.case_id as caseId,a.cust_id as custId,a.location as location,a.assigned_user as assignedUser,a.customer_name as customerName,a.customer_no as customerNo,a.risk_severity as riskSeverity,a.channel as chennel,a.account_no as accountNo,a.cashflow_type as cashflowType,a.currency as currency,a.txn_amt as txnAmount,a.txn_datetime as txnDtTm,a.txn_no as txnNo,a.txn_type as txnType,a.remark as remark,"
			+ " SUM(a.alert_status='WIP') as wipcount, SUM(a.alert_status='Close') as closecount FROM alert a WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 WEEK) < created_date) group by created_date", nativeQuery = true)
	List<AlertPerformance> getOneWeekAlertPerformace(String bankCode);
	
	@Query(value = " SELECT SUM(alert_status='WIP') WipCount, SUM(alert_status='Close') CloseCount FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < created_date) group by created_date", nativeQuery = true)
	List<AlertPerformance> getOneMonthAlertPerformace(String bankCode);

	@Query(value = " SELECT SUM(alert_status='WIP') WipCount, SUM(alert_status='Close') CloseCount FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 QUARTER) < created_date) group by created_date", nativeQuery = true)
	List<AlertPerformance> getOneQuarterAlertPerformace(String bankCode);
	
	@Query(value = " SELECT SUM(alert_status='WIP') WipCount, SUM(alert_status='Close') CloseCount FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 6 MONTH) < created_date) group by created_date", nativeQuery = true)
	List<AlertPerformance> getSixMonthAlertPerformace(String bankCode);

	@Query(value = " SELECT SUM(alert_status='WIP') WipCount, SUM(alert_status='Close') CloseCount FROM alert WHERE bank_code =:bankCode and (DATE_SUB(CURDATE(), INTERVAL 1 YEAR) < created_date) group by created_date", nativeQuery = true)
	List<AlertPerformance> getOneYearAlertPerformace(String bankCode);
}
