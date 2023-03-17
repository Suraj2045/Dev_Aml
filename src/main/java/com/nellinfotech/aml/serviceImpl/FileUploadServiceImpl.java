package com.nellinfotech.aml.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.Account;
import com.nellinfotech.aml.entities.AccountFail;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.CustomerFail;
import com.nellinfotech.aml.entities.FileUploadLog;
import com.nellinfotech.aml.entities.LookupDtls;
import com.nellinfotech.aml.entities.LookupMst;
import com.nellinfotech.aml.entities.SdnDetails;
import com.nellinfotech.aml.entities.Transaction;
import com.nellinfotech.aml.entities.TransactionFail;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.AccountFailRepository;
import com.nellinfotech.aml.repository.AccountRepository;
import com.nellinfotech.aml.repository.Bankrepository;
import com.nellinfotech.aml.repository.CustomerFailRepository;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.FileUploadLogRepository;
import com.nellinfotech.aml.repository.LookupDtlsRepository;
import com.nellinfotech.aml.repository.LookupMstRepository;
import com.nellinfotech.aml.repository.TransactionFailRepository;
import com.nellinfotech.aml.repository.TransactionRepository;
import com.nellinfotech.aml.service.FileUploadService;
import com.nellinfotech.aml.service.SwiftMessageService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    Bankrepository bankResp;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountFailRepository accountFailRepository;
    @Autowired
    FileUploadLogRepository fileuploadRepository;
    @Autowired
    CustomerRepository cutomerRepository;
    @Autowired
    CustomerFailRepository customerFailRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionFailRepository transactionFailRepository;
    @Autowired
    LookupMstRepository lookupMstRepo;
    @Autowired
    LookupDtlsRepository lookupDtlsRepo;
    @Autowired
    private SpringSecurityConfig securityConfig;
    @Autowired
     SwiftMessageService swiftService;
    
    @Autowired
	Environment env;
    
    Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    @Override
    public ResponseEntity<Map<String, Object>> uploadAccountExcel(HttpHeaders httpHeaders) {
        try {
        	logger.info(" inside FileUploadServiceImpl - uploadAccountExcel-" );
        	
            List<LookupDtls> accountType = getLookupDetails("Account Type");
            Header header = HeaderValidator.headerConversion(httpHeaders);
            FileInputStream fis = new FileInputStream(new File(securityConfig.getPropertyValue("accountFile")));
            //read excel from location
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            
            Row headerRow = sheet.getRow(0);
            Iterator<Cell> cells = headerRow.cellIterator();
            Map<String, Integer> data = new HashMap<String, Integer>();
            int count = 0;
            /// generate random number 
            Random r = new Random();
            long logId = (long) (1293861599 + r.nextDouble() * 60 * 60 * 24 * 365);
            FileUploadLog fileLog = new FileUploadLog();
            while (cells.hasNext()) {
                Cell cell = (Cell) cells.next();
                RichTextString value = cell.getRichStringCellValue();
                data.put(securityConfig.getPropertyValue(value.getString()), count);
                count = count + 1;
            }
            int passCount = 0;
            int failCount = 0;
            List<Account>existingAccountList=new ArrayList<Account>();
            existingAccountList=accountRepository.findAll();
            List<Account> accountList = new ArrayList<Account>();
            List<AccountFail> accountFailList = new ArrayList<AccountFail>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                
                Account account = new Account();
                AccountFail accountFail = new AccountFail();
                StringBuilder sb = new StringBuilder();
                Date date = new Date();
                System.out.println(new Timestamp(date.getTime()));
                int flag = 0;
                Row row = (Row) sheet.getRow(i);
                int actype = 0;
                //set cell value
                Cell acctType1, acctSubType, acNo, custCode, acctCategory, acctStatus, acctBaseBr, acctHasNominee,
                        acctPrdCode, status, acctClosedDt, acctCreatedDt, acctOprnMode;
                
                acctType1 = row.getCell(data.get("acctType"));
                acctSubType = row.getCell(data.get("acctSubType"));
                acNo = row.getCell(data.get("acNo"));
                custCode = row.getCell(data.get("custCode"));
                acctCategory = row.getCell(data.get("acctCategory"));
                acctStatus = row.getCell(data.get("acctStatus"));
                acctBaseBr = row.getCell(data.get("acctBaseBr"));
                acctHasNominee = row.getCell(data.get("acctHasNominee"));
                acctPrdCode = row.getCell(data.get("acctPrdCode"));
                status = row.getCell(data.get("status"));
                acctClosedDt = row.getCell(data.get("acctClosedDt"));
                acctCreatedDt = row.getCell(data.get("acctCreatedDt"));
                acctOprnMode = row.getCell(data.get("acctOprnMode"));
                //end cell value
                for (int j = 0; j < accountType.size(); j++) {
                    String act = acctType1.toString();
                    
                    if (accountType.get(j).getLookupValueCode().toString().equals(act)) {
                        actype = actype + 1;
                    }
                }
                
                if (acNo == null) {
                    sb.append("A/C No is empty,");
                    flag++;
                }
                if (custCode == null) {
                    sb.append("custCode is empty,");
                    flag++;
                }
                
                if (flag > 0) {
                    failCount++;
                    if (acNo != null) {
                        accountFail.setAcctNo(row.getCell(data.get("acNo")).toString());
                    }
                    if (custCode == null) {
                        accountFail.setCustCode("0");
                    } else {
                        accountFail.setCustCode(row.getCell(data.get("custCode")).toString());
                    }
                    if (actype > 0) {
                        accountFail.setAcctType(row.getCell(data.get("acctType")).toString());
                    }
                    if (acctCategory != null) {
                        accountFail.setAcctCategory(acctCategory.toString());
                    }
                    if (acctStatus != null) {
                        accountFail.setAcctStatus(acctStatus.toString());
                    }
                    if (acctBaseBr != null) {
                        accountFail.setAcctBaseBr(acctBaseBr.toString());
                    }
                    if (acctHasNominee != null) {
                        float rex = Float.parseFloat(acctHasNominee.toString());
                        accountFail.setAcctHasNominee(Math.round(rex));
                    }
                    if (acctPrdCode != null) {
                        accountFail.setAcctPrdCode(acctPrdCode.toString());
                    }
                    if (status != null) {
                        accountFail.setStatus(status.toString());
                    }
                    if (acctClosedDt != null) {
                        accountFail.setAcctClosedDt(new Date(acctClosedDt.toString()));
                    }
                    if (acctCreatedDt != null) {
                        accountFail.setAcctCreatedDt(new Date(acctCreatedDt.toString()));
                    }
                    if (acctOprnMode != null) {
                        accountFail.setAcctOprnMode(Double.parseDouble(acctOprnMode.toString()));
                    }
                    accountFail.setAccountLogId(logId);
                    accountFail.setBankCode(env.getProperty("bankCode"));
                    accountFail.setCreatedBy(env.getProperty("userId"));
                    
                    accountFail.setAccountLogId(logId);
                    accountFail.setStatus("fail");
                    accountFail.setReason(sb.toString());
                    if (acctSubType != null) {
                        account.setAcctSubType(acctSubType.toString());
                    }
                    if (custCode != null) {
                        accountFail.setCustCode(custCode.toString());
                    }
                    accountFailList.add(accountFail);
                    
                } else {
                    passCount++;
                    if(existingAccountList.size()>0)
                    {
                    	for (Account extAccountDetails : existingAccountList) {
							if(extAccountDetails.getAcctNo().contentEquals(acNo.toString()))
							{
								account.setId(extAccountDetails.getId());
							}
						}
                    }
                    account.setAcctNo(acNo.toString());
                    if (actype > 0) {
                        account.setAcctType(acctType1.toString());
                    }
                    
                    if (acctCategory != null) {
                        account.setAcctCategory(acctCategory.toString());
                    }
                    if (acctStatus != null) {
                        account.setAcctStatus(acctStatus.toString());
                    }
                    if (acctBaseBr != null) {
                        account.setAcctBaseBr(acctBaseBr.toString());
                    }
                    if (acctHasNominee != null) {
                        float rex = Float.parseFloat(acctHasNominee.toString());
                        account.setAcctHasNominee(Math.round(rex));
                    }
                    if (acctPrdCode != null) {
                        account.setAcctPrdCode(acctPrdCode.toString());
                    }
                    if (status != null) {
                        account.setStatus(status.toString());
                    }
                    if (acctClosedDt != null) {
                        account.setAcctClosedDt(new Date(acctClosedDt.toString()));
                    }
                    if (acctCreatedDt != null) {
                        account.setAcctCreatedDt(new Date(acctCreatedDt.toString()));
                    }
                    if (acctOprnMode != null) {
                        account.setAcctOprnMode(Double.parseDouble(acctOprnMode.toString()));
                    }
                    account.setAccountLogId(logId);
                    account.setCustCode(custCode.toString());
                    account.setBankCode(env.getProperty("bankCode"));
                    account.setCreatedBy(env.getProperty("userId"));
                    account.setAccountLogId(logId);
                    if (acctSubType != null) {
                        account.setAcctSubType(acctSubType.toString());
                    }
                    fileLog.setFileName("account" + logId);
                    account.setStatus("pass");
                    accountList.add(account);
                }
                
            }
            fileLog.setAccountLogId(logId);
            fileLog.setPassCount(passCount);
            fileLog.setFailCount(failCount);
            fileLog.setBankCode(env.getProperty("bankCode"));
            fileLog.setCreatedBy(env.getProperty("userId"));
            accountRepository.saveAll(accountList);
            accountFailRepository.saveAll(accountFailList);
            fileuploadRepository.save(fileLog);
            
        } catch (Exception e) {
        	logger.error("Error getFailRecordExcel"+e);
        }
        return null;
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> uploadCustomerData(HttpHeaders httpHeaders) {
        try {	Header header = HeaderValidator.headerConversion(httpHeaders);
            FileInputStream fis = new FileInputStream(new File(securityConfig.getPropertyValue("customerFile")));
            logger.info(" inside FileUploadServiceImpl - uploadCustomerData-" );
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            
            Row headerRow = sheet.getRow(0);
            // List<String> headers = new ArrayList<String>();
            Iterator<Cell> cells = headerRow.cellIterator();
            Map<String, Integer> data = new HashMap<String, Integer>();
            int count = 0;
            /// generate random number 
            List<LookupDtls> Lookuptitle = getLookupDetails("Title");
            List<LookupDtls> lookupcustomerType = getLookupDetails("Customer Type");
            Random r = new Random();
            long logId = (long) (1293861599 + r.nextDouble() * 60 * 60 * 24 * 365);
            FileUploadLog fileLog = new FileUploadLog();
            while (cells.hasNext()) {
                Cell cell = (Cell) cells.next();
                RichTextString value = cell.getRichStringCellValue();
                data.put(securityConfig.getPropertyValue(value.getString()), count);
                count = count + 1;
            }
            int passCount = 0;
            int failCount = 0;
         	 // sdnList=swiftService.findAll();
            List<Customer> existingCustomerList=new ArrayList<Customer>();
            existingCustomerList=cutomerRepository.findAll();
            List<Customer> customerList = new ArrayList<Customer>();
            List<CustomerFail> customerFailList = new ArrayList<CustomerFail>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Customer customer = new Customer();
                CustomerFail customerFail = new CustomerFail();
                StringBuilder sb = new StringBuilder();
                Date date = new Date();
                System.out.println(new Timestamp(date.getTime()));
                int flag = 0;
                Row row = (Row) sheet.getRow(i);
                ///
                Cell custRefId, customerType, customerSubType, customerCategory, customerGender, title, customerName,
                        customerFName, customerMName, yearsOfService, totalYearOfService, noOfDependants,
                        currAnnualIncome, qualification, customerArName, customerOtherName, customerDOB,
                        customerBirthPlace, customerRiskCalc, customerPrevRiskCalc, riskManual, dailyIncome,
                        monthlyIncome, baseBrCode, rmCode, rmName, remarks, lastDueDiligenceDate, referredBy,
                        nationalId1, nationalIdType1, nationalId2, nationalIdType2, panNo, aadhaarNo, passportNo,
                        passportIssuePlace, passportExpiryDate, passportCountry, isPEP, isHNI, addr1, addr2, addr3,
                        city, state, country, postalCode, isSTRReported, isCTRReported, industryType, customerOpenDate,
                        binNo, charityYN, corpregNo, countryOfReg, emailId, mobileNo;
                
                System.out.println(row.getCell(data.get("custRefId")));
                
                custRefId = row.getCell(data.get("custRefId"));
                
                customerType = row.getCell(data.get("customerType"));
                customerSubType = row.getCell(data.get("customerSubType"));
                customerCategory = row.getCell(data.get("customerCategory"));
                customerGender = row.getCell(data.get("customerGender"));
                title = row.getCell(data.get("title"));
                customerName = row.getCell(data.get("customerName"));
                customerFName = row.getCell(data.get("customerFName"));
                customerMName = row.getCell(data.get("customerMName"));
                yearsOfService = row.getCell(data.get("yearsOfService"));
                totalYearOfService = row.getCell(data.get("totalYearOfService"));
                noOfDependants = row.getCell(data.get("noOfDependants"));
                customerBirthPlace = row.getCell(data.get("customerBirthPlace"));
                customerRiskCalc = row.getCell(data.get("customerRiskCalc"));
                customerPrevRiskCalc = row.getCell(data.get("customerPrevRiskCalc"));
                riskManual = row.getCell(data.get("riskManual"));
                dailyIncome = row.getCell(data.get("dailyIncome"));
                monthlyIncome = row.getCell(data.get("monthlyIncome"));
                baseBrCode = row.getCell(data.get("baseBrCode"));
                rmCode = row.getCell(data.get("rmCode"));
                rmName = row.getCell(data.get("rmName"));
                remarks = row.getCell(data.get("remarks"));
                lastDueDiligenceDate = row.getCell(data.get("lastDueDiligenceDate"));
                referredBy = row.getCell(data.get("referredBy"));
                nationalId1 = row.getCell(data.get("nationalId1"));
                nationalIdType1 = row.getCell(data.get("nationalIdType1"));
                nationalId2 = row.getCell(data.get("nationalId2"));
                nationalIdType2 = row.getCell(data.get("nationalIdType2"));
                panNo = row.getCell(data.get("panNo"));
                aadhaarNo = row.getCell(data.get("aadhaarNo"));
                passportNo = row.getCell(data.get("passportNo"));
                passportIssuePlace = row.getCell(data.get("passportIssuePlace"));
                passportExpiryDate = row.getCell(data.get("passportExpiryDate"));
                passportCountry = row.getCell(data.get("passportCountry"));
                isPEP = row.getCell(data.get("isPEP"));
                isHNI = row.getCell(data.get("isHNI"));
                addr1 = row.getCell(data.get("addr1"));
                addr2 = row.getCell(data.get("addr2"));
                addr3 = row.getCell(data.get("addr3"));
                city = row.getCell(data.get("city"));
                state = row.getCell(data.get("state"));
                country = row.getCell(data.get("country"));
                postalCode = row.getCell(data.get("postalCode"));
                isSTRReported = row.getCell(data.get("isSTRReported"));
                isCTRReported = row.getCell(data.get("isCTRReported"));
                industryType = row.getCell(data.get("industryType"));
                customerOpenDate = row.getCell(data.get("customerOpenDate"));
                currAnnualIncome = row.getCell(data.get("currAnnualIncome"));
                qualification = row.getCell(data.get("qualification"));
                customerArName = row.getCell(data.get("customerArName"));
                customerOtherName = row.getCell(data.get("customerOtherName"));
                customerDOB = row.getCell(data.get("customerDOB"));
                binNo = row.getCell(data.get("binNo"));
                charityYN = row.getCell(data.get("charityYN"));
                corpregNo = row.getCell(data.get("corpregNo"));
                countryOfReg = row.getCell(data.get("countryOfReg"));
                emailId = row.getCell(data.get("emailId"));
                mobileNo = row.getCell(data.get("mobileNo"));
                Cell custCode = row.getCell(data.get("custCode"));
                List<SdnDetails>sdnList=new ArrayList<SdnDetails>();
                int sdnChk=0;
                sdnList=swiftService.findByFirstName(customerFName.toString());
                for (SdnDetails sdnDetails : sdnList) {
					if(sdnDetails.getFirstName().contentEquals(customerFName.toString()))
					{
						sdnChk=sdnChk+1;
					}
				}
                int titlecount = 0;
                for (int j = 0; j < Lookuptitle.size(); j++) {
                    String ltitle = title.toString();
                    
                    if (Lookuptitle.get(j).getLookupValueCode().toString().equals(ltitle)) {
                        titlecount = titlecount + 1;
                    }
                }
                
                int customerCount = 0;
                for (int j = 0; j < lookupcustomerType.size(); j++) {
                    String customerTy = customerType.toString();
                    
                    if (Lookuptitle.get(j).getLookupValueCode().toString().equals(customerTy)) {
                        customerCount = customerCount + 1;
                    }
                }
               
                if (custCode == null) {
                    sb.append("custCode is empty,");
                    flag++;
                }
               
                if (flag > 0) {
                    failCount++;
                    if (custCode != null) {
                        customerFail.setCustCode(custCode.toString());
                    } else {
                        customerFail.setCustCode(" ");
                    }
                    if (custRefId != null) {
                        customerFail.setCustRefId(custRefId.toString());
                    }
                    if (customerType != null && customerCount > 0) {
                        customerFail.setCustomerType(customerType.toString());
                    }
                    if (customerSubType != null) {
                        customerFail.setCustomerSubType(customerSubType.toString());
                    }
                    if (customerCategory != null) {
                        customerFail.setCustomerCategory(customerCategory.toString());
                    }
                    if (customerGender != null) {
                        customerFail.setCustomerGender(customerGender.toString());
                    }
                    if (title != null && titlecount > 0) {
                        customerFail.setTitle(title.toString());
                    }
                    if (customerName != null) {
                        customerFail.setCustomerName(customerName.toString());
                    }
                    if (customerFName != null) {
                        customerFail.setCustomerFName(customerFName.toString());
                    }
                    if (customerMName != null) {
                        customerFail.setCustomerMName(customerMName.toString());
                    }
                    if (yearsOfService != null) {
                        customerFail.setYearsOfService(Double.parseDouble(yearsOfService.toString()));
                    }
                    if (noOfDependants != null) {
                        float rex = Float.parseFloat(noOfDependants.toString());
                        customerFail.setNoOfDependants(Math.round(rex));
                    }
                    if (customerBirthPlace != null) {
                        customerFail.setCustomerBirthPlace(customerBirthPlace.toString());
                    }
                    if (customerRiskCalc != null) {
                        customerFail.setCustomerRiskCalc(Double.parseDouble(customerRiskCalc.toString()));
                    }
                    if (customerPrevRiskCalc != null) {
                        customerFail.setCustomerPrevRiskCalc(Double.parseDouble(customerPrevRiskCalc.toString()));
                    }
                    if (riskManual != null) {
                        customerFail.setRiskManual(Double.parseDouble(riskManual.toString()));
                    }
                    if (dailyIncome != null) {
                        customerFail.setDailyIncome(BigDecimal.valueOf(Double.parseDouble(dailyIncome.toString())));
                    }
                    if (monthlyIncome != null) {
                        customerFail.setMonthlyIncome(BigDecimal.valueOf(Double.parseDouble(monthlyIncome.toString())));
                    }
                    if (baseBrCode != null) {
                        customerFail.setBaseBrCode(baseBrCode.toString());
                    }
                    if (rmCode != null) {
                        customerFail.setRmCode(rmCode.toString());
                    }
                    if (rmName != null) {
                        customerFail.setRmName(rmName.toString());
                    }
                    if (remarks != null) {
                    	if(sdnChk>0)
                    	{
                    		customerFail.setRemarks("black Listed");
                    	}else
                    	{
                        customerFail.setRemarks(remarks.toString());
                    	}
                    }
                    if (lastDueDiligenceDate != null) {
                        customerFail.setLastDueDiligenceDate(new Date(lastDueDiligenceDate.toString()));
                    }
                    if (referredBy != null) {
                        customerFail.setReferredBy(referredBy.toString());
                    }
                    if (nationalId1 != null) {
                        customerFail.setNationalId1(nationalId1.toString());
                    }
                    if (nationalId2 != null) {
                        customerFail.setNationalId2(nationalId2.toString());
                    }
                    if (nationalIdType1 != null) {
                        customerFail.setNationalIdType1(nationalIdType1.toString());
                    }
                    if (nationalIdType2 != null) {
                        customerFail.setNationalIdType2(nationalIdType2.toString());
                    }
                    if (totalYearOfService != null) {
                        customerFail.setTotalYearOfService(Double.parseDouble(totalYearOfService.toString()));
                    }
                    if (panNo != null) {
                        customerFail.setPanNo(panNo.toString());
                    }
                    if (aadhaarNo != null) {
                        customerFail.setNationalIdType2(aadhaarNo.toString());
                    }
                    if (passportNo != null) {
                        customerFail.setPassportNo(passportNo.toString());
                    }
                    if (passportIssuePlace != null) {
                        customerFail.setPassportIssuePlace(passportIssuePlace.toString());
                    }
                    if (passportExpiryDate != null) {
                        customerFail.setPassportExpiryDate(new Date(passportExpiryDate.toString()));
                    }
                    if (passportCountry != null) {
                        customerFail.setPassportCountry(passportCountry.toString());
                    }
                    if (isPEP != null) {
                        float rex = Float.parseFloat(isPEP.toString());
                        customerFail.setIsPEP(Math.round(rex));
                    }
                    if (isHNI != null) {
                        customerFail.setIsHNI(Integer.parseInt(isHNI.toString()));
                    }
                    if (addr1 != null) {
                        customerFail.setAddr1(addr1.toString());
                    }
                    if (addr2 != null) {
                        customerFail.setAddr2(addr2.toString());
                    }
                    if (addr3 != null) {
                        customerFail.setAddr3(addr3.toString());
                    }
                    if (city != null) {
                        customerFail.setCity(city.toString());
                    }
                    if (state != null) {
                        customerFail.setState(state.toString());
                    }
                    if (country != null) {
                        customerFail.setCountry(country.toString());
                    }
                    if (postalCode != null) {
                        customerFail.setPostalCode(postalCode.toString());
                    }
                    if (isSTRReported != null) {
                        float rex = Float.parseFloat(noOfDependants.toString());
                        customerFail.setIsSTRReported(Math.round(rex));
                    }
                    if (isCTRReported != null) {
                        float rex = Float.parseFloat(isCTRReported.toString());
                        customerFail.setIsCTRReported(Math.round(rex));
                    }
                    if (industryType != null) {
                        customerFail.setIndustryType(industryType.toString());
                    }
                    if (customerOpenDate != null) {
                        customerFail.setCustomerOpenDate(new Date(customerOpenDate.toString()));
                    }
                    
                    if (currAnnualIncome != null) {
                        customerFail.setCurrAnnualIncome(Double.parseDouble(currAnnualIncome.toString()));
                    }
                    if (qualification != null) {
                        customerFail.setQualification(qualification.toString());
                    }
                    if (customerArName != null) {
                        customerFail.setCustomerArName(customerArName.toString());
                    }
                    if (customerOtherName != null) {
                        customerFail.setCustomerOtherName(customerOtherName.toString());
                    }
                    if (customerDOB != null) {
                        customerFail.setCustomerDOB(new Date(customerDOB.toString()));
                    }
                    if (binNo != null) {
                        customerFail.setBinNo(binNo.toString());
                    }
                    if (charityYN != null) {
                        float rex = Float.parseFloat(charityYN.toString());
                        customerFail.setCharityYN(Math.round(rex));
                    }
                    if (corpregNo != null) {
                        customerFail.setCorpregNo(corpregNo.toString());
                    }
                    if (countryOfReg != null) {
                        customerFail.setCountryOfReg(countryOfReg.toString());
                    }
                    if (emailId != null) {
                        customerFail.setEmailId(emailId.toString());
                    }
                    if (mobileNo != null) {
                        customerFail.setMobileNo(mobileNo.toString());
                    }
                    customerFail.setAccountLogId(logId);
                    customerFail.setBankCode(env.getProperty("bankCode"));
                    customerFail.setCreatedBy(env.getProperty("userId"));
                    customerFail.setAccountLogId(logId);
                    customerFail.setStatus("fail");
                    customerFail.setReason(sb.toString());
                    customerFailList.add(customerFail);
                    
                } else {
                    passCount++;
                    
                    if(existingCustomerList.size()>0)
                    {
                    	for (Customer existCustomer : existingCustomerList) {
                    		if(existCustomer.getCustCode().contentEquals(custCode.toString()))
                    		{
                    			customer.setId(existCustomer.getId());
                    		}
							
						}
                    }
                    if (custCode != null) {
                        customer.setCustCode(custCode.toString());
                    }
                    if (custRefId != null) {
                        customer.setCustRefId(custRefId.toString());
                    }
                    if (customerType != null && customerCount > 0) {
                        customer.setCustomerType(customerType.toString());
                    }
                    if (customerSubType != null) {
                        customer.setCustomerSubType(customerSubType.toString());
                    }
                    if (customerCategory != null) {
                        customer.setCustomerCategory(customerCategory.toString());
                    }
                    if (customerGender != null) {
                        customer.setCustomerGender(customerGender.toString());
                    }
                    if (title != null && titlecount > 0) {
                        customer.setTitle(title.toString());
                    }
                    if (customerFName != null) {
                        customer.setCustomerFName(customerFName.toString());
                    }
                    if (customerName != null) {
                        customer.setCustomerName(customerName.toString());
                    }
                    if (customerMName != null) {
                        customer.setCustomerMName(customerMName.toString());
                    }
                    if (yearsOfService != null) {
                        customer.setYearsOfService(Double.parseDouble(yearsOfService.toString()));
                    }
                    if (noOfDependants != null) {
                        float rex = Float.parseFloat(noOfDependants.toString());
                        customer.setNoOfDependants(Math.round(rex));
                    }
                    if (customerBirthPlace != null) {
                        customer.setCustomerBirthPlace(customerBirthPlace.toString());
                    }
                    if (customerRiskCalc != null) {
                        customer.setCustomerRiskCalc(Double.parseDouble(customerRiskCalc.toString()));
                    }
                    if (customerPrevRiskCalc != null) {
                        customer.setCustomerPrevRiskCalc(Double.parseDouble(customerPrevRiskCalc.toString()));
                    }
                    if (riskManual != null) {
                        customer.setRiskManual(Double.parseDouble(riskManual.toString()));
                    }
                    if (dailyIncome != null) {
                        customer.setDailyIncome(BigDecimal.valueOf(Double.parseDouble(dailyIncome.toString())));
                    }
                    if (monthlyIncome != null) {
                        customer.setMonthlyIncome(BigDecimal.valueOf(Double.parseDouble(monthlyIncome.toString())));
                    }
                    if (baseBrCode != null) {
                        customer.setBaseBrCode(baseBrCode.toString());
                    }
                    if (rmCode != null) {
                        customer.setRmCode(rmCode.toString());
                    }
                    if (rmName != null) {
                        customer.setRmName(rmName.toString());
                    }
                    if (remarks != null) {
                    	if(sdnChk>0)
                    	{
                    		customer.setRemarks("black Listed");
                    	}else
                    	{
                    		customer
                    		.setRemarks(remarks.toString());
                    	}
                    }
                    if (lastDueDiligenceDate != null) {
                        customer.setLastDueDiligenceDate(new Date(lastDueDiligenceDate.toString()));
                    }
                    if (referredBy != null) {
                        customerFail.setReferredBy(referredBy.toString());
                    }
                    if (nationalId1 != null) {
                        customer.setNationalId1(nationalId1.toString());
                    }
                    if (nationalId2 != null) {
                        customer.setNationalId2(nationalId2.toString());
                    }
                    if (nationalIdType1 != null) {
                        customer.setNationalIdType1(nationalIdType1.toString());
                    }
                    if (nationalIdType2 != null) {
                        customer.setNationalIdType2(nationalIdType2.toString());
                    }
                    if (panNo != null) {
                        customer.setPanNo(panNo.toString());
                    }
                    if (aadhaarNo != null) {
                        customer.setAadhaarNo(aadhaarNo.toString());
                    }
                    if (passportNo != null) {
                        customer.setPassportNo(passportNo.toString());
                    }
                    if (passportIssuePlace != null) {
                        customer.setPassportIssuePlace(passportIssuePlace.toString());
                    }
                    if (passportExpiryDate != null) {
                        customer.setPassportExpiryDate(new Date(passportExpiryDate.toString()));
                    }
                    if (passportCountry != null) {
                        customer.setPassportCountry(passportCountry.toString());
                    }
                    if (isPEP != null) {
                        float rex = Float.parseFloat(isPEP.toString());
                        customer.setIsPEP(Math.round(rex));
                    }
                    
                    if (isHNI != null) {
                        float rex = Float.parseFloat(isHNI.toString());
                        customer.setIsHNI(Math.round(rex));
                    }
                    if (addr1 != null) {
                        customer.setAddr1(addr1.toString());
                    }
                    if (addr2 != null) {
                        customer.setAddr2(addr2.toString());
                    }
                    if (addr3 != null) {
                        customer.setAddr3(addr3.toString());
                    }
                    if (city != null) {
                        customer.setCity(city.toString());
                    }
                    if (state != null) {
                        customer.setState(state.toString());
                    }
                    if (country != null) {
                        customer.setCountry(country.toString());
                    }
                    if (postalCode != null) {
                        customer.setPostalCode(postalCode.toString());
                    }
                    if (isSTRReported != null) {
                        float rex = Float.parseFloat(noOfDependants.toString());
                        customer.setIsSTRReported(Math.round(rex));
                    }
                    if (isCTRReported != null) {
                        float rex = Float.parseFloat(isCTRReported.toString());
                        customer.setIsCTRReported(Math.round(rex));
                    }
                    if (totalYearOfService != null) {
                        customer.setTotalYearOfService(Double.parseDouble(totalYearOfService.toString()));
                    }
                    if (industryType != null) {
                        customer.setIndustryType(industryType.toString());
                    }
                    if (customerOpenDate != null) {
                        customer.setCustomerOpenDate(new Date(customerOpenDate.toString()));
                    }
                    
                    if (currAnnualIncome != null) {
                        customer.setCurrAnnualIncome(Double.parseDouble(currAnnualIncome.toString()));
                    }
                    if (qualification != null) {
                        customer.setQualification(qualification.toString());
                    }
                    if (customerArName != null) {
                        customer.setCustomerArName(customerArName.toString());
                    }
                    if (customerOtherName != null) {
                        customer.setCustomerOtherName(customerOtherName.toString());
                    }
                    if (customerDOB != null) {
                        customer.setCustomerDOB(new Date(customerDOB.toString()));
                    }
                    if (binNo != null) {
                        customer.setBinNo(binNo.toString());
                    }
                    if (charityYN != null) {
                        float rex = Float.parseFloat(charityYN.toString());
                        customer.setCharityYN(Math.round(rex));
                    }
                    if (corpregNo != null) {
                        customer.setCorpregNo(corpregNo.toString());
                    }
                    if (countryOfReg != null) {
                        customer.setCountryOfReg(countryOfReg.toString());
                    }
                    if (emailId != null) {
                        customer.setEmailId(emailId.toString());
                    }
                    if (mobileNo != null) {
                        customer.setMobileNo(mobileNo.toString());
                    }
                    
                    customer.setAccountLogId(logId);
                    customer.setBankCode(env.getProperty("bankCode"));
                    customer.setCreatedBy(env.getProperty("userId"));
                    customer.setAccountLogId(logId);
                    customer.setStatus("pass");
                    customerList.add(customer);
                }
                
            }
            fileLog.setAccountLogId(logId);
            fileLog.setPassCount(passCount);
            fileLog.setFailCount(failCount);
            fileLog.setBankCode(env.getProperty("bankCode"));
            
            fileLog.setCreatedBy(env.getProperty("userId"));
            fileLog.setFileName("Customer_" + logId);
            cutomerRepository.saveAll(customerList);
            customerFailRepository.saveAll(customerFailList);
            fileuploadRepository.save(fileLog);
            
        } catch (Exception e) {
        	logger.error("Error getFailRecordExcel"+e);
        }
        return null;
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> uploadTransactionData(HttpHeaders httpHeaders) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            FileInputStream fis = new FileInputStream(new File(securityConfig.getPropertyValue("transactionFile")));
            logger.info(" inside FileUploadServiceImpl - uploadCustomerData-" );
            
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            
            Row headerRow = sheet.getRow(0);
            // List<String> headers = new ArrayList<String>();
            Iterator<Cell> cells = headerRow.cellIterator();
            Map<String, Integer> data = new HashMap<String, Integer>();
            int count = 0;
            /// generate random number 
            Random r = new Random();
            long logId = (long) (1293861599 + r.nextDouble() * 60 * 60 * 24 * 365);
            FileUploadLog fileLog = new FileUploadLog();
            while (cells.hasNext()) {
                Cell cell = (Cell) cells.next();
                RichTextString value = cell.getRichStringCellValue();
                data.put(securityConfig.getPropertyValue(value.getString()), count);
                count = count + 1;
            }
            int passCount = 0;
            int failCount = 0;
            
            List<Transaction> transactionList = new ArrayList<Transaction>();
            List<TransactionFail> transactionFailList = new ArrayList<TransactionFail>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Transaction transaction = new Transaction();
                TransactionFail transactionFail = new TransactionFail();
                StringBuilder sb = new StringBuilder();
                Date date = new Date();
                System.out.println(new Timestamp(date.getTime()));
                int flag = 0;
                Row row = (Row) sheet.getRow(i);
                //
                Cell custCode, acctNo, ruleExecuted, txnNo, txnCode, txnSetNo, txnScrollNo, txnTokenNo, txnBatchCode,
                        txnDtTm, valueDtTm, postingDtTm, txnType, txnSubType, cashflowType, drPartyName, crPartyName,
                        txnAmount, acBalAmt, txnCharges, txnBranchCode, txnStatus, txnCreatedBy, txnCreatedDtTm,
                        txnAuthBy, txnAuthDtTm;
                //
                custCode = row.getCell(data.get("custCode"));
                acctNo = row.getCell(data.get("acctNo"));
                ruleExecuted = row.getCell(data.get("ruleExecuted"));
                txnNo = row.getCell(data.get("txnNo"));
                txnCode = row.getCell(data.get("txnCode"));
                txnSetNo = row.getCell(data.get("txnSetNo"));
                txnScrollNo = row.getCell(data.get("txnScrollNo"));
                txnTokenNo = row.getCell(data.get("txnTokenNo"));
                txnBatchCode = row.getCell(data.get("txnBatchCode"));
                txnDtTm = row.getCell(data.get("txnDtTm"));
                valueDtTm = row.getCell(data.get("valueDtTm"));
                postingDtTm = row.getCell(data.get("postingDtTm"));
                txnType = row.getCell(data.get("txnType"));
                txnSubType = row.getCell(data.get("txnSubType"));
                cashflowType = row.getCell(data.get("cashflowType"));
                drPartyName = row.getCell(data.get("drPartyName"));
                txnAmount = row.getCell(data.get("txnAmount"));
                acBalAmt = row.getCell(data.get("acBalAmt"));
                txnCharges = row.getCell(data.get("txnCharges"));
                txnBranchCode = row.getCell(data.get("txnBranchCode"));
                txnStatus = row.getCell(data.get("txnStatus"));
                txnCreatedBy = row.getCell(data.get("txnCreatedBy"));
                txnCreatedDtTm = row.getCell(data.get("txnCreatedDtTm"));
                txnAuthBy = row.getCell(data.get("txnAuthBy"));
                txnAuthDtTm = row.getCell(data.get("txnAuthDtTm"));
                crPartyName = row.getCell(data.get("crPartyName"));
                if (custCode == null) {
                    sb.append("custCode is empty,");
                    flag++;
                }
                if (acctNo == null) {
                    sb.append("acNo is empty,");
                    flag++;
                }
                if (ruleExecuted == null) {
                    sb.append("ruleExecuted is empty,");
                    flag++;
                }
                if (txnNo == null) {
                    sb.append("txnNo is empty,");
                    flag++;
                }
                if (txnCode == null) {
                    sb.append("txnCode is empty,");
                    flag++;
                }
                if (txnSetNo == null) {
                    sb.append("txnSetNo is empty,");
                    flag++;
                }
                if (txnScrollNo == null) {
                    sb.append("txnScrollNo is empty,");
                    flag++;
                }
                if (txnTokenNo == null) {
                    sb.append("txnTokenNo is empty,");
                    flag++;
                }
                if (txnBatchCode == null) {
                    sb.append("txnBatchCode is empty,");
                    flag++;
                }
                
                ///validation
                
                if (flag > 0) {
                    
                    failCount++;
                    if (custCode != null) {
                        transactionFail.setCustCode(custCode.toString());
                    } else {
                        transactionFail.setCustCode("0");
                    }
                    if (acctNo != null) {
                        transactionFail.setAcctNo(acctNo.toString());
                        
                    } else {
                        transactionFail.setAcctNo("0");
                    }
                    
                    if (ruleExecuted != null) {
                        float rex = Float.parseFloat(ruleExecuted.toString());
                        transactionFail.setRuleExecuted(Math.round(rex));
                        
                    } else {
                        transactionFail.setRuleExecuted(0);
                    }
                    if (txnNo != null) {
                        transactionFail.setTxnNo(txnNo.toString());
                    } else {
                        transactionFail.setTxnNo("0");
                    }
                    if (txnCode != null) {
                        transactionFail.setTxnCode(txnCode.toString());
                    } else {
                        transactionFail.setTxnCode("0");
                    }
                    if (txnSetNo != null) {
                        transactionFail.setTxnSetNo(txnSetNo.toString());
                    } else {
                        transactionFail.setTxnSetNo("0");
                    }
                    if (txnScrollNo != null) {
                        transactionFail.setTxnScrollNo(txnScrollNo.toString());
                    } else {
                        transactionFail.setTxnScrollNo("0");
                    }
                    if (txnTokenNo != null) {
                        transactionFail.setTxnTokenNo(txnTokenNo.toString());
                    } else {
                        transactionFail.setTxnTokenNo("0");
                    }
                    if (txnBatchCode != null) {
                        transactionFail.setTxnBatchCode(txnBatchCode.toString());
                    } else {
                        transactionFail.setTxnBatchCode("0");
                    }
                    
                    if (txnDtTm != null) {
                        transactionFail.setTxnDtTm(new Date(txnDtTm.toString()));
                    }
                    if (valueDtTm != null) {
                        transactionFail.setValueDtTm(new Date(valueDtTm.toString()));
                    }
                    if (postingDtTm != null) {
                        transactionFail.setPostingDtTm(new Date(postingDtTm.toString()));
                    }
                    if (txnType != null) {
                        transactionFail.setTxnType(txnType.toString());
                    }
                    if (txnSubType != null) {
                        transactionFail.setTxnSubType(txnSubType.toString());
                    }
                    if (cashflowType != null) {
                        transactionFail.setCashflowType(cashflowType.toString());
                    }
                    if (drPartyName != null) {
                        transactionFail.setDrPartyName(drPartyName.toString());
                    }
                    if (acBalAmt != null) {
                        transactionFail.setAcBalAmt(new BigDecimal(Double.parseDouble(acBalAmt.toString())));
                    }
                    if (txnCharges != null) {
                        transactionFail.setTxnCharges(new BigDecimal(Double.parseDouble(txnCharges.toString())));
                    }
                    if (txnBranchCode != null) {
                        transactionFail.setTxnBatchCode(txnBatchCode.toString());
                    }
                    if (txnStatus != null) {
                        transactionFail.setTxnStatus(txnStatus.toString());
                    }
                    if (txnDtTm != null) {
                        transactionFail.setTxnDtTm(new Date(txnDtTm.toString()));
                    }
                    
                    if (txnCreatedDtTm != null) {
                        transactionFail.setTxnCreatedDtTm(new Date(txnCreatedDtTm.toString()));
                    }
                    if (txnAuthBy != null) {
                        transactionFail.setTxnAuthBy(txnAuthBy.toString());
                    }
                    if (txnAuthDtTm != null) {
                        transactionFail.setTxnAuthDtTm(new Date(txnAuthDtTm.toString()));
                    }
                    if (txnCreatedBy != null) {
                        transactionFail.setTxnCreatedBy(txnCreatedBy.toString());
                    }
                    if (txnAmount != null) {
                        transactionFail.setTxnAmount(new BigDecimal(Double.parseDouble(txnAmount.toString())));
                    }
                    if (txnBranchCode != null) {
                        transactionFail.setTxnBranchCode(txnBranchCode.toString());
                    }
                    if (crPartyName != null) {
                        transactionFail.setCrPartyName(crPartyName.toString());
                    }
                    transactionFail.setAccountLogId(logId);
                    transactionFail.setBankCode(env.getProperty("bankCode"));
                    transactionFail.setCreatedBy(env.getProperty("userId"));
                    transactionFail.setAccountLogId(logId);
                    transactionFail.setStatus("fail");
                    transactionFail.setReason(sb.toString());
                    transactionFailList.add(transactionFail);
                    
                } else {
                    passCount++;
                    transaction.setAcctNo(row.getCell(data.get("acctNo")).toString());
                    transaction.setCustCode(row.getCell(data.get("custCode")).toString());
                    float rex = Float.parseFloat(row.getCell(data.get("ruleExecuted")).toString());
                    transaction.setRuleExecuted(Math.round(rex));
                    transaction.setTxnNo(txnNo.toString());
                    transaction.setTxnCode(row.getCell(data.get("txnCode")).toString());
                    transaction.setTxnSetNo(row.getCell(data.get("txnSetNo")).toString());
                    transaction.setTxnScrollNo(row.getCell(data.get("txnScrollNo")).toString());
                    transaction.setTxnTokenNo(row.getCell(data.get("txnTokenNo")).toString());
                    transaction.setTxnBatchCode(row.getCell(data.get("txnBatchCode")).toString());
                    if (txnDtTm != null) {
                        transaction.setTxnDtTm(new Date(txnDtTm.toString()));
                    }
                    if (valueDtTm != null) {
                        transaction.setValueDtTm(new Date(valueDtTm.toString()));
                    }
                    if (postingDtTm != null) {
                        transaction.setPostingDtTm(new Date(postingDtTm.toString()));
                    }
                    if (txnType != null) {
                        transaction.setTxnType(txnType.toString());
                    }
                    if (txnSubType != null) {
                        transaction.setTxnSubType(txnSubType.toString());
                    }
                    if (cashflowType != null) {
                        transaction.setCashflowType(cashflowType.toString());
                    }
                    if (drPartyName != null) {
                        transaction.setDrPartyName(drPartyName.toString());
                    }
                    if (acBalAmt != null) {
                        transaction.setAcBalAmt(new BigDecimal(Double.parseDouble(acBalAmt.toString())));
                    }
                    if (txnCharges != null) {
                        transaction.setTxnCharges(new BigDecimal(Double.parseDouble(txnCharges.toString())));
                    }
                    if (txnBranchCode != null) {
                        transaction.setTxnBatchCode(txnBatchCode.toString());
                    }
                    if (txnStatus != null) {
                        transaction.setTxnStatus(txnStatus.toString());
                    }
                    
                    if (txnCreatedBy != null) {
                        transaction.setTxnCreatedBy(txnCreatedBy.toString());
                    }
                    
                    if (txnCreatedDtTm != null) {
                        System.out.println(txnCreatedDtTm);
                        transaction.setTxnCreatedDtTm(new Date(txnCreatedDtTm.toString()));
                    }
                    if (txnAuthBy != null) {
                        transaction.setTxnAuthBy(txnAuthBy.toString());
                    }
                    if (txnAuthDtTm != null) {
                        transaction.setTxnAuthDtTm(new Date(txnAuthDtTm.toString()));
                    }
                    if (txnAmount != null) {
                        transaction.setTxnAmount(new BigDecimal(Double.parseDouble(txnAmount.toString())));
                    }
                    if (txnBranchCode != null) {
                        transaction.setTxnBranchCode(txnBranchCode.toString());
                    }
                    if (crPartyName != null) {
                        transaction.setCrPartyName(crPartyName.toString());
                    }
                    transaction.setAccountLogId(logId);
                    transaction.setBankCode(env.getProperty("bankCode"));
                    transaction.setCreatedBy(env.getProperty("userId"));
                    transaction.setStatus("pass");
                    transactionList.add(transaction);
                }
                
            }
            fileLog.setAccountLogId(logId);
            fileLog.setPassCount(passCount);
            fileLog.setFailCount(failCount);
            fileLog.setBankCode(env.getProperty("bankCode"));
            fileLog.setCreatedBy(env.getProperty("userId"));
            fileLog.setFileName("Transaction_" + logId);
            transactionRepository.saveAll(transactionList);
            transactionFailRepository.saveAll(transactionFailList);
            fileuploadRepository.save(fileLog);
            
        } catch (Exception e) {
        	logger.error("Error getFailRecordExcel"+e);
        }
        return null;
    }
    
    public List<LookupDtls> getLookupDetails(String type) {
        try {
        	logger.info(" inside FileUploadServiceImpl - getLookupDetails-" );
            List<LookupMst> lookup = new ArrayList<LookupMst>();
            List<LookupDtls> lookupDtls = new ArrayList<LookupDtls>();
            List<LookupDtls> lookupdetails = new ArrayList<LookupDtls>();
            lookup = lookupMstRepo.findAll();
            lookupDtls = lookupDtlsRepo.findAll();
            
            for (LookupMst lookupmst : lookup) {
                if (lookupmst.getLookupType().equals(type)) {
                    for (LookupDtls lookupDtls2 : lookupDtls) {
                        
                        if (lookupmst.getLookupCode().equals(lookupDtls2.getLookupCode())) {
                            lookupdetails.add(lookupDtls2);
                        }
                    }
                }
            }
            
            return lookupdetails;
        } catch (Exception e) {
        	logger.error("Error getFailRecordExcel"+e);
        }
        return null;
    }
    
    //---------------
    @Override
    public ResponseEntity<Resource> getFailRecordExcel() {
        try {
        	logger.info(" inside FileUploadServiceImpl - getFailRecordExcel-" );
            String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps";
            List<AccountFail> accountFailList = new ArrayList<AccountFail>();
            AccountFail accountHeader = new AccountFail();
            accountHeader.setAcctNo("Account No");
            accountHeader.setAcctType("Account Type");
            
            accountHeader.setStatus("Account Status");
            accountHeader.setReason("Account Reason");
            String status = "fail";
            accountFailList = accountFailRepository.findByStatus(status);
            XSSFWorkbook workbook = new XSSFWorkbook();
            String dir = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/account";
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdir();
                System.out.println("Directory is created!");
            } else {
                deleteFolder(file);
                System.out.println("Deleted Directory is already existed!");
                file.mkdir();
                System.out.println(" New Directory is created!");
            }
            //Create a blank sheet
            XSSFSheet sheet = workbook.createSheet(" Employee Info ");
            //Create row object
            //  XSSFRow row;  
            int rowCount = 0;
            for (int i = 0; i < accountFailList.size(); i++) {
                if (i == 0) {
                    XSSFRow row = sheet.createRow(++rowCount);
                    writeBook(accountHeader, row);
                } else {
                    XSSFRow row = sheet.createRow(++rowCount);
                    writeBook(accountFailList.get(i), row);
                }
            }
            FileOutputStream out = new FileOutputStream(new File(dir + "/account.xlsx"));
            workbook.write(out);
            out.close();
            
            File file1 = new File(dir + "/account.xlsx");
            
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=account.xlsx");
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");
            
            Path path1 = Paths.get(file1.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path1));
            
            return ResponseEntity.ok().headers(header).contentLength(file1.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
        } catch (Exception e) {
           logger.error("Error getFailRecordExcel"+e);
        }
        return null;
    }
    
    private void writeBook(AccountFail accountFail, Row row) {
    	logger.info(" inside FileUploadServiceImpl - writeBook-" );
        Cell cell = row.createCell(1);
        cell.setCellValue(accountFail.getAcctNo());
        
        cell = row.createCell(2);
        cell.setCellValue(accountFail.getAcctType());
        
        cell = row.createCell(3);
        cell.setCellValue(accountFail.getStatus());
        
        cell = row.createCell(4);
        cell.setCellValue(accountFail.getReason());
        
    }
    
    //-------------
    public void deleteFolder(File file) {
    	logger.info(" inside FileUploadServiceImpl - deleteFolder-" );
        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
    
   
}
