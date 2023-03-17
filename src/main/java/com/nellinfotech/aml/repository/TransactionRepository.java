package com.nellinfotech.aml.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.Transaction;
//import com.nellinfotech.aml.service.Employee;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	
	@Query(value = " SELECT * FROM transaction WHERE acct_no =:acctNo || cust_code =:custCode || cashflow_type =:cashflowType || txn_amt =:operator=:txnAmount"
			+ "|| (txn_datetime BETWEEN :toDate and :fromDate)", nativeQuery = true)
	List<Transaction> findByTransactionParam(String acctNo, String custCode, String cashflowType, BigDecimal txnAmount, String toDate, String fromDate, String operator);
	
	@Query(value = " SELECT * FROM transaction WHERE txn_no =:txnNumber and cust_code =:custCode", nativeQuery = true)
	Transaction getTransactionDetails(String txnNumber, String custCode);

	@Query(value = " SELECT * FROM transaction WHERE acct_no =:acctNo and cust_code =:custCode and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < txn_datetime)", nativeQuery = true)	
	List<Transaction> getTransactionBy1MonthInterval(String acctNo, String custCode);
	
	@Query(value = " SELECT * FROM transaction WHERE acct_no =:acctNo and cust_code =:custCode and (DATE_SUB(CURDATE(), INTERVAL 3 MONTH) < txn_datetime)", nativeQuery = true)
	List<Transaction> getTransactionBy3MonthInterval(String acctNo, String custCode);

	@Query(value = " SELECT * FROM transaction WHERE acct_no =:acctNo and cust_code =:custCode and (DATE_SUB(CURDATE(), INTERVAL 1 WEEK) < txn_datetime)", nativeQuery = true)
	List<Transaction> getTransactionBy1WeekInterval(String acctNo, String custCode);

	@Query(value = " SELECT * FROM transaction WHERE acct_no =:acctNo and cust_code =:custCode and (DATE_SUB(CURDATE(), INTERVAL 6 MONTH) < txn_datetime)", nativeQuery = true)
	List<Transaction> getTransactionBy6MonthInterval(String acctNo, String custCode);

	@Query(value = " SELECT * FROM transaction WHERE acct_no =:acctNo and cust_code =:custCode and (DATE_SUB(CURDATE(), INTERVAL 1 YEAR) < txn_datetime)", nativeQuery = true)
	List<Transaction> getTransactionBy1YearInterval(String acctNo, String custCode);
	    
	@Query(value = " SELECT * FROM transaction WHERE cust_code=:custCode and EXTRACT(YEAR_MONTH FROM txn_datetime) = EXTRACT(YEAR_MONTH FROM CURDATE() - "
			+ "INTERVAL 1 MONTH)", nativeQuery = true)
	List<Transaction> getATMWithdrawlCount(String custCode);
	
	@Query(value = " SELECT * FROM transaction", nativeQuery = true)
	List<Transaction> getCustATMWithdrawlCount();
	//SELECT * FROM transaction
	//WHERE MONTH(txn_datetime) = MONTH(CURRENT_TIMESTAMP) AND YEAR(txn_datetime) = YEAR(CURRENT_TIMESTAMP);
	@Query(value="SELECT * FROM `transaction` WHERE  txn_datetime between  DATE_FORMAT(CURDATE(),'%Y-%m-01') AND CURDATE()",nativeQuery=true)
	List<Transaction> findRecords();

	//@Query(value="SELECT * FROM transaction WHERE bank_code=:bankCode and txn_amt>=:txn_amt and txn_type=:txn_type and cashflow_type=:dr_cr and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < txn_datetime)",nativeQuery=true)
	@Query(value=" SELECT sum(txn_amt) as txn_amt,id,auth_status,bank_code,created_by,created_date,is_active,modified_by, " + 
			" modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee, " + 
			" txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code,txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel " + 
			" FROM transaction WHERE bank_code=:bankCode and txn_amt>=:txn_amt and txn_type=:txn_type and cashflow_type=:dr_cr and CURDATE() = txn_datetime ",nativeQuery=true)
	List<Transaction> findDailyRecords(String bankCode, Double txn_amt, String txn_type, String dr_cr);

	@Query(value = " SELECT * FROM transaction WHERE (created_date BETWEEN :fromDate and :toDate)", nativeQuery = true)
	List<Transaction> getTransactionByAccountNo( String toDate, String fromDate);

//	SELECT * FROM transaction WHERE txn_amt>=:txn_amt and txn_type=:txn_type and (select customer_type from customer where cust_code=:cust_code)=:cust_type and cashflow_type=:cashflow_type and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < txn_datetime) 
	
	@Query(value=" SELECT sum(txn_amt) as txn_amt,id,auth_status,bank_code,created_by,created_date,is_active,modified_by, " + 
			"  modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee, " + 
			" txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code,txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel " 
			+ " FROM transaction WHERE bank_code=:bankCode and txn_amt>=:txn_amt and txn_type=:txn_type and cashflow_type=:dr_cr and (DATE_SUB(CURDATE(), INTERVAL 1 WEEK) < txn_datetime)",nativeQuery=true)
	List<Transaction> findWeeklyRecords(String bankCode, Double txn_amt, String txn_type, String dr_cr);

	@Query(value="  SELECT sum(txn_amt) as txn_amt,id,auth_status,bank_code,created_by,created_date,is_active,modified_by, " + 
			" modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee, " + 
			" txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code,txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel " + 
			 " FROM transaction WHERE bank_code=:bankCode and txn_amt>=:txn_amt and txn_type=:txn_type and cashflow_type=:dr_cr and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < txn_datetime)",nativeQuery=true)
	List<Transaction> findMonthlyRecords(String bankCode, Double txn_amt, String txn_type, String dr_cr);
	
	@Query(value=" SELECT sum(txn_amt) as txn_amt,id,auth_status,bank_code,created_by,created_date,is_active,modified_by, " + 
			" modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee, " + 
			" txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code,txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel "
			+" FROM transaction WHERE bank_code=:bankCode and txn_amt>=:txn_amt and txn_type=:txn_type and cashflow_type=:dr_cr and (DATE_SUB(CURDATE(), INTERVAL 1 QUARTER) < txn_datetime)",nativeQuery=true)
	List<Transaction> findQuarterlyRecords(String bankCode, Double txn_amt, String txn_type, String dr_cr);
	
	@Query(value=" SELECT sum(txn_amt) as txn_amt,id,auth_status,bank_code,created_by,created_date,is_active,modified_by,"
			+ " modified_date,version,ac_bal_amt,acct_no,cashflow_type,txn_cr_partynamee,cust_code,txn_drcr,txn_dr_partynamee,"
			+ " txn_post_datetime,rule_executed,txn_auth_by,txn_auth_datetime,txn_batch_code,txn_br_code,txn_charges,txn_code,txn_created_by,txn_created_datetime,txn_datetime,txn_no,txn_scroll_no,txn_set_no,txn_status,txn_subtype,txn_token_no,txn_type,upload_count,upload_datetime,txn_value_datetime,account_log_id,status,channel "
			+ " FROM transaction WHERE bank_code=:bankCode and txn_amt>=:txn_amt and txn_type=:txn_type and cashflow_type=:dr_cr and (DATE_SUB(CURDATE(), INTERVAL 1 YEAR) < txn_datetime) group by acct_no",nativeQuery=true)
	List<Transaction> findYearlyRecords(String bankCode, Double txn_amt, String txn_type, String dr_cr);

//	SELECT * FROM transaction WHERE txn_amt>=:txn_amt and txn_type=:txn_type and (select customer_type from customer where cust_code=:cust_code)=:cust_type and cashflow_type=:cashflow_type and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < txn_datetime) 
	
//	@Procedure("checkTransaction")
//	int getTrans();
/////SELECT * FROM transaction WHERE txn_amt>=10000 and txn_type='SA' and cashflow_type='cr' and (DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < txn_datetime)

//
//	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode group by alert_status", nativeQuery = true)
//	List<Alert> getAlertStatusbyGroup(String bankCode);
//	
//	@Query(value = " SELECT * FROM alert WHERE bank_code =:bankCode group by alert_code", nativeQuery = true)
//	List<Alert> getAlertNamebyGroup(String bankCode);

}
