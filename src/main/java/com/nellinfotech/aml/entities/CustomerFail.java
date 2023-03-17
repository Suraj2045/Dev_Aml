package com.nellinfotech.aml.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Customer_Fail")
public class CustomerFail extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -4290229289973014265L;
    
    @Column(name = "CUST_CODE", nullable = false, length = 25)
    
    private String custCode = "";
    
    // Read from Sequence Table
    
    @Column(name = "UPLOAD_COUNT", nullable = true)
    
    private Integer uploadCount;
    
    @Column(name = "UPLOAD_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date uploadDateTime = Calendar.getInstance().getTime();
    
    @Column(name = "CUST_REF_ID", nullable = true, length = 25)
    
    private String custRefId = "";
    
    @Column(name = "CUSTOMER_TYPE", nullable = true, length = 25)
    
    private String customerType = ""; // From Lookup(CUSTOMER_TYPE)-USER e.g.: IND, COR, SOC, TRT
    
    @Column(name = "CUSTOMER_SUB_TYPE", nullable = true, length = 25)
    
    private String customerSubType = "";
    
    @Column(name = "CUSTOMER_CATE", nullable = true, length = 25)
    
    private String customerCategory = "";
    
    @Column(name = "CUSTOMER_GENDER", nullable = true, length = 25)
    
    private String customerGender = "";
    
    @Column(name = "CUSTOMER_TITLE", nullable = true, length = 25)
    
    private String title = ""; // From LOOKUP(TITLE)-USER e.g.: Mr., Ms., Dr., etc.
    
    @Column(name = "CUSTOMER_NAME", nullable = true, length = 75)
    
    private String customerName = "";
    
    @Column(name = "CUSTOMER_FNAME", nullable = true, length = 50)
    
    private String customerFName = "";
    
    @Column(name = "CUSTOMER_MNAME", nullable = true, length = 50)
    
    private String customerMName = "";
    
    @Column(name = "CUSTOMER_LNAME", nullable = true, length = 50)
    
    private String customerLName = "";
    
    @Column(name = "YEARS_OF_SERVICE", nullable = true)
    
    private Double yearsOfService;
    
    @Column(name = "TOTAL_YEAR_OF_SERVICE", nullable = true)
    
    private Double totalYearOfService;
    
    @Column(name = "NO_OF_DEPENDENTS", nullable = true)
    
    private Integer noOfDependants = 0;
    
    @Column(name = "CURRENT_ANNUAL_INCOME", nullable = true)
    
    private Double currAnnualIncome;
    
    @Column(name = "QUALIFICATION", nullable = true, length = 25)
    
    private String qualification = "";
    
    // Arabic Name
    
    @Column(name = "CUSTOMER_AR_NAME", nullable = true, length = 25)
    
    private String customerArName = "";
    
    @Column(name = "CUSTOMER_OTHER_NAME", nullable = true, length = 75)
    
    private String customerOtherName = "";
    
    @Column(name = "CUSTOMER_DOB", nullable = true)
    
    @Temporal(TemporalType.DATE)
    
    private Date customerDOB;
    
    @Column(name = "CUSTOMER_BIRTH_PLACE", nullable = true, length = 25)
    
    private String customerBirthPlace = "";
    
    @Column(name = "CUSTOMER_RATING_CALC", nullable = true)
    
    private Double customerRiskCalc;
    
    @Column(name = "CUSTOMER_PREV_RATING_CALC", nullable = true)
    
    private Double customerPrevRiskCalc;
    
    @Column(name = "CUSTOMER_RISK_MANUAL", nullable = true)
    
    private Double riskManual = 0.0D;
    
    @Column(name = "CUSTOMER_DAILY_INCOME", nullable = true, precision = 16, scale = 4)
    
    private BigDecimal dailyIncome;
    
    @Column(name = "CUSTOMER_MONTHLY_INCOME", nullable = true, precision = 16, scale = 4)
    
    private BigDecimal monthlyIncome;
    
    @Column(name = "BASE_BR_CODE", nullable = true, length = 25)
    
    private String baseBrCode = "";
    
    @Column(name = "RM_CODE", nullable = true, length = 25)
    
    private String rmCode = "";
    
    @Column(name = "RM_NAME", nullable = true, length = 50)
    
    private String rmName = "";
    
    @Column(name = "REMARKS", nullable = true, length = 100)
    
    private String remarks = "";
    
    @Column(name = "LAST_DUE_DILIGENCE_DATE", nullable = true)
    
    @Temporal(TemporalType.DATE)
    
    private Date lastDueDiligenceDate;
    
    @Column(name = "REFERRED_BY", nullable = true, length = 25)
    
    private String referredBy = "";
    
    @Column(name = "NATIONAL_ID_1", nullable = true, length = 25)
    
    private String nationalId1 = "";
    
    @Column(name = "NATIONAL_ID_TYPE1", nullable = true, length = 25)
    
    private String nationalIdType1 = "";
    
    @Column(name = "NATIONAL_ID_2", nullable = true, length = 25)
    
    private String nationalId2 = "";
    
    @Column(name = "NATIONAL_ID_TYPE2", nullable = true, length = 25)
    
    private String nationalIdType2 = "";
    
    @Column(name = "CUSTOMER_PAN", nullable = true, length = 12)
    
    private String panNo = "";
    
    @Column(name = "CUSTOMER_AADHAAR", nullable = true, length = 14)
    
    private String aadhaarNo = "";
    
    @Column(name = "CUSTOMER_PASSPORT", nullable = true, length = 25)
    
    private String passportNo = "";
    
    @Column(name = "PASSPORT_ISSUE_PLACE", nullable = true, length = 25)
    
    private String passportIssuePlace = "";
    
    @Column(name = "PASSPORT_EXPIRY_DATE", nullable = true)
    
    @Temporal(TemporalType.DATE)
    
    private Date passportExpiryDate;
    
    @Column(name = "PASSPORT_COUNTRY", nullable = true, length = 25)
    
    private String passportCountry = "";
    
    @Column(name = "IS_PEP", nullable = true)
    
    private Integer isPEP;
    
    @Column(name = "IS_HNI", nullable = true)
    
    private Integer isHNI;
    
    @Column(name = "ADDR1", nullable = true, length = 50)
    
    private String addr1 = "";
    
    @Column(name = "ADDR2", nullable = true, length = 50)
    
    private String addr2 = "";
    
    @Column(name = "ADDR3", nullable = true, length = 50)
    
    private String addr3 = "";
    
    @Column(name = "ADDR_CTY", nullable = true, length = 25)
    
    private String city = "";
    
    @Column(name = "ADDR_STATE", nullable = true, length = 25)
    
    private String state = "";
    
    @Column(name = "ADDR_COUNTRY", nullable = true, length = 25)
    
    private String country = "";
    
    @Column(name = "ADDR_POSTALCODE", nullable = true, length = 15)
    
    private String postalCode = "";
    
    @Column(name = "IS_STR_REPORTED", nullable = true)
    
    private Integer isSTRReported;
    
    @Column(name = "IS_CTR_REPORTED", nullable = true)
    
    private Integer isCTRReported;
    
    @Column(name = "INDUSTRY_TYPE", nullable = true, length = 25)
    
    private String industryType = ""; // From LOOKUP(INDUSTRY_TYPE) USER
    
    @Column(name = "CUSTOMER_OPEN_DATE", nullable = true)
    
    @Temporal(TemporalType.DATE)
    
    private Date customerOpenDate;
    
    @Column(name = "BIN_NO", nullable = true, length = 35)
    
    private String binNo = "";
    
    @Column(name = "TIN_NO", nullable = true, length = 35)
    
    private String tinNo = "";
    
    @Column(name = "CUSTOMER_STATUS", nullable = true, length = 25)
    
    private String customerStatus = "";
    
    @Column(name = "NATIONALITY", nullable = true, length = 25)
    
    private String nationality = "";
    
    @Column(name = "INTRODUCER", nullable = true, length = 50)
    
    private String introducer = "";
    
    @Column(name = "CURRENT_COUNTRY", nullable = true, length = 25)
    
    private String currentCountry = "";
    
    @Column(name = "SOURCE_OF_FUND", nullable = true, length = 25)
    
    private String sourceOfFund = "";
    
    @Column(name = "DT_OF_COMMENCE", nullable = true)
    
    @Temporal(TemporalType.DATE)
    
    private Date dateOfComm;
    
    @Column(name = "CHARITY_YN", nullable = true)
    
    private Integer charityYN;
    
    @Column(name = "SHORT_NAME", nullable = true, length = 25)
    
    private String shortName = "";
    
    @Column(name = "EMPLOYER_NAME", nullable = true, length = 50)
    
    private String employersName = "";
    
    @Column(name = "TYPE_OF_BUSINESS", nullable = true, length = 25)
    
    private String typeOfBusiness = "";
    
    @Column(name = "REGISTRATION_COUNTRY", nullable = true, length = 25)
    
    private String countryOfReg = "";
    
    @Column(name = "CORP_REG_NO", nullable = true, length = 25)
    
    private String corpregNo = "";
    
    @Column(name = "REG_DATE", nullable = true)
    
    @Temporal(TemporalType.DATE)
    
    private Date dateOfReg;
    
    @Column(name = "PHONE_NO", nullable = true, length = 25)
    
    private String phoneNo = "";
    
    @Column(name = "MOBILE_NO", nullable = true, length = 25)
    
    private String mobileNo = "";
    
    @Column(name = "EMAIL_ID", nullable = true, length = 50)
    
    private String emailId = "";
    
    @Column(name = "ANNUAL_TURNOVER", nullable = true, precision = 16, scale = 4)
    
    private BigDecimal annTurnover;
    
    @Column(name = "SPOUSE_NAME", nullable = true, length = 25)
    
    private String spouseName = "";
    
    @Column(name = "IS_SELF_EMPLOYED_YN", nullable = true)
    
    private Integer isSelfEmployedYN;
    
    @Column(name = "RESI_VISA_YN", nullable = true)
    
    private Integer resiVisaYN;
    
    @Column(name = "STATUS", nullable = true, length = 25)
    private String status;
    
    @Column(name = "REASON", nullable = true, length = 25)
    private String reason;
    
    @Column(name = "ACCOUNT_LOG_ID", nullable = true, length = 25)
    private long accountLogId;
    
    public String getCustCode() {
        return custCode;
    }
    
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public Integer getUploadCount() {
        return uploadCount;
    }
    
    public void setUploadCount(Integer uploadCount) {
        this.uploadCount = uploadCount;
    }
    
    public Date getUploadDateTime() {
        return uploadDateTime;
    }
    
    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
    
    public String getCustRefId() {
        return custRefId;
    }
    
    public void setCustRefId(String custRefId) {
        this.custRefId = custRefId;
    }
    
    public String getCustomerType() {
        return customerType;
    }
    
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    
    public String getCustomerSubType() {
        return customerSubType;
    }
    
    public void setCustomerSubType(String customerSubType) {
        this.customerSubType = customerSubType;
    }
    
    public String getCustomerCategory() {
        return customerCategory;
    }
    
    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }
    
    public String getCustomerGender() {
        return customerGender;
    }
    
    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerFName() {
        return customerFName;
    }
    
    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }
    
    public String getCustomerMName() {
        return customerMName;
    }
    
    public void setCustomerMName(String customerMName) {
        this.customerMName = customerMName;
    }
    
    public String getCustomerLName() {
        return customerLName;
    }
    
    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }
    
    public Double getYearsOfService() {
        return yearsOfService;
    }
    
    public void setYearsOfService(Double yearsOfService) {
        this.yearsOfService = yearsOfService;
    }
    
    public Double getTotalYearOfService() {
        return totalYearOfService;
    }
    
    public void setTotalYearOfService(Double totalYearOfService) {
        this.totalYearOfService = totalYearOfService;
    }
    
    public Integer getNoOfDependants() {
        return noOfDependants;
    }
    
    public void setNoOfDependants(Integer noOfDependants) {
        this.noOfDependants = noOfDependants;
    }
    
    public Double getCurrAnnualIncome() {
        return currAnnualIncome;
    }
    
    public void setCurrAnnualIncome(Double currAnnualIncome) {
        this.currAnnualIncome = currAnnualIncome;
    }
    
    public String getQualification() {
        return qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    public String getCustomerArName() {
        return customerArName;
    }
    
    public void setCustomerArName(String customerArName) {
        this.customerArName = customerArName;
    }
    
    public String getCustomerOtherName() {
        return customerOtherName;
    }
    
    public void setCustomerOtherName(String customerOtherName) {
        this.customerOtherName = customerOtherName;
    }
    
    public Date getCustomerDOB() {
        return customerDOB;
    }
    
    public void setCustomerDOB(Date customerDOB) {
        this.customerDOB = customerDOB;
    }
    
    public String getCustomerBirthPlace() {
        return customerBirthPlace;
    }
    
    public void setCustomerBirthPlace(String customerBirthPlace) {
        this.customerBirthPlace = customerBirthPlace;
    }
    
    public Double getCustomerRiskCalc() {
        return customerRiskCalc;
    }
    
    public void setCustomerRiskCalc(Double customerRiskCalc) {
        this.customerRiskCalc = customerRiskCalc;
    }
    
    public Double getCustomerPrevRiskCalc() {
        return customerPrevRiskCalc;
    }
    
    public void setCustomerPrevRiskCalc(Double customerPrevRiskCalc) {
        this.customerPrevRiskCalc = customerPrevRiskCalc;
    }
    
    public Double getRiskManual() {
        return riskManual;
    }
    
    public void setRiskManual(Double riskManual) {
        this.riskManual = riskManual;
    }
    
    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }
    
    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }
    
    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }
    
    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    
    public String getBaseBrCode() {
        return baseBrCode;
    }
    
    public void setBaseBrCode(String baseBrCode) {
        this.baseBrCode = baseBrCode;
    }
    
    public String getRmCode() {
        return rmCode;
    }
    
    public void setRmCode(String rmCode) {
        this.rmCode = rmCode;
    }
    
    public String getRmName() {
        return rmName;
    }
    
    public void setRmName(String rmName) {
        this.rmName = rmName;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Date getLastDueDiligenceDate() {
        return lastDueDiligenceDate;
    }
    
    public void setLastDueDiligenceDate(Date lastDueDiligenceDate) {
        this.lastDueDiligenceDate = lastDueDiligenceDate;
    }
    
    public String getReferredBy() {
        return referredBy;
    }
    
    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }
    
    public String getNationalId1() {
        return nationalId1;
    }
    
    public void setNationalId1(String nationalId1) {
        this.nationalId1 = nationalId1;
    }
    
    public String getNationalIdType1() {
        return nationalIdType1;
    }
    
    public void setNationalIdType1(String nationalIdType1) {
        this.nationalIdType1 = nationalIdType1;
    }
    
    public String getNationalId2() {
        return nationalId2;
    }
    
    public void setNationalId2(String nationalId2) {
        this.nationalId2 = nationalId2;
    }
    
    public String getNationalIdType2() {
        return nationalIdType2;
    }
    
    public void setNationalIdType2(String nationalIdType2) {
        this.nationalIdType2 = nationalIdType2;
    }
    
    public String getPanNo() {
        return panNo;
    }
    
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }
    
    public String getAadhaarNo() {
        return aadhaarNo;
    }
    
    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }
    
    public String getPassportNo() {
        return passportNo;
    }
    
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }
    
    public String getPassportIssuePlace() {
        return passportIssuePlace;
    }
    
    public void setPassportIssuePlace(String passportIssuePlace) {
        this.passportIssuePlace = passportIssuePlace;
    }
    
    public Date getPassportExpiryDate() {
        return passportExpiryDate;
    }
    
    public void setPassportExpiryDate(Date passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }
    
    public String getPassportCountry() {
        return passportCountry;
    }
    
    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }
    
    public Integer getIsPEP() {
        return isPEP;
    }
    
    public void setIsPEP(Integer isPEP) {
        this.isPEP = isPEP;
    }
    
    public Integer getIsHNI() {
        return isHNI;
    }
    
    public void setIsHNI(Integer isHNI) {
        this.isHNI = isHNI;
    }
    
    public String getAddr1() {
        return addr1;
    }
    
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }
    
    public String getAddr2() {
        return addr2;
    }
    
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }
    
    public String getAddr3() {
        return addr3;
    }
    
    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public Integer getIsSTRReported() {
        return isSTRReported;
    }
    
    public void setIsSTRReported(Integer isSTRReported) {
        this.isSTRReported = isSTRReported;
    }
    
    public Integer getIsCTRReported() {
        return isCTRReported;
    }
    
    public void setIsCTRReported(Integer isCTRReported) {
        this.isCTRReported = isCTRReported;
    }
    
    public String getIndustryType() {
        return industryType;
    }
    
    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }
    
    public Date getCustomerOpenDate() {
        return customerOpenDate;
    }
    
    public void setCustomerOpenDate(Date customerOpenDate) {
        this.customerOpenDate = customerOpenDate;
    }
    
    public String getBinNo() {
        return binNo;
    }
    
    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }
    
    public String getTinNo() {
        return tinNo;
    }
    
    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }
    
    public String getCustomerStatus() {
        return customerStatus;
    }
    
    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getIntroducer() {
        return introducer;
    }
    
    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }
    
    public String getCurrentCountry() {
        return currentCountry;
    }
    
    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }
    
    public String getSourceOfFund() {
        return sourceOfFund;
    }
    
    public void setSourceOfFund(String sourceOfFund) {
        this.sourceOfFund = sourceOfFund;
    }
    
    public Date getDateOfComm() {
        return dateOfComm;
    }
    
    public void setDateOfComm(Date dateOfComm) {
        this.dateOfComm = dateOfComm;
    }
    
    public Integer getCharityYN() {
        return charityYN;
    }
    
    public void setCharityYN(Integer charityYN) {
        this.charityYN = charityYN;
    }
    
    public String getShortName() {
        return shortName;
    }
    
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String getEmployersName() {
        return employersName;
    }
    
    public void setEmployersName(String employersName) {
        this.employersName = employersName;
    }
    
    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }
    
    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }
    
    public String getCountryOfReg() {
        return countryOfReg;
    }
    
    public void setCountryOfReg(String countryOfReg) {
        this.countryOfReg = countryOfReg;
    }
    
    public String getCorpregNo() {
        return corpregNo;
    }
    
    public void setCorpregNo(String corpregNo) {
        this.corpregNo = corpregNo;
    }
    
    public Date getDateOfReg() {
        return dateOfReg;
    }
    
    public void setDateOfReg(Date dateOfReg) {
        this.dateOfReg = dateOfReg;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public BigDecimal getAnnTurnover() {
        return annTurnover;
    }
    
    public void setAnnTurnover(BigDecimal annTurnover) {
        this.annTurnover = annTurnover;
    }
    
    public String getSpouseName() {
        return spouseName;
    }
    
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
    
    public Integer getIsSelfEmployedYN() {
        return isSelfEmployedYN;
    }
    
    public void setIsSelfEmployedYN(Integer isSelfEmployedYN) {
        this.isSelfEmployedYN = isSelfEmployedYN;
    }
    
    public Integer getResiVisaYN() {
        return resiVisaYN;
    }
    
    public void setResiVisaYN(Integer resiVisaYN) {
        this.resiVisaYN = resiVisaYN;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public long getAccountLogId() {
        return accountLogId;
    }
    
    public void setAccountLogId(long accountLogId) {
        this.accountLogId = accountLogId;
    }
    
    @Override
    public String toString() {
        return "CustomerFail [custCode=" + custCode + ", uploadCount=" + uploadCount + ", uploadDateTime="
                + uploadDateTime + ", custRefId=" + custRefId + ", customerType=" + customerType + ", customerSubType="
                + customerSubType + ", customerCategory=" + customerCategory + ", customerGender=" + customerGender
                + ", title=" + title + ", customerName=" + customerName + ", customerFName=" + customerFName
                + ", customerMName=" + customerMName + ", customerLName=" + customerLName + ", yearsOfService="
                + yearsOfService + ", totalYearOfService=" + totalYearOfService + ", noOfDependants=" + noOfDependants
                + ", currAnnualIncome=" + currAnnualIncome + ", qualification=" + qualification + ", customerArName="
                + customerArName + ", customerOtherName=" + customerOtherName + ", customerDOB=" + customerDOB
                + ", customerBirthPlace=" + customerBirthPlace + ", customerRiskCalc=" + customerRiskCalc
                + ", customerPrevRiskCalc=" + customerPrevRiskCalc + ", riskManual=" + riskManual + ", dailyIncome="
                + dailyIncome + ", monthlyIncome=" + monthlyIncome + ", baseBrCode=" + baseBrCode + ", rmCode=" + rmCode
                + ", rmName=" + rmName + ", remarks=" + remarks + ", lastDueDiligenceDate=" + lastDueDiligenceDate
                + ", referredBy=" + referredBy + ", nationalId1=" + nationalId1 + ", nationalIdType1=" + nationalIdType1
                + ", nationalId2=" + nationalId2 + ", nationalIdType2=" + nationalIdType2 + ", panNo=" + panNo
                + ", aadhaarNo=" + aadhaarNo + ", passportNo=" + passportNo + ", passportIssuePlace="
                + passportIssuePlace + ", passportExpiryDate=" + passportExpiryDate + ", passportCountry="
                + passportCountry + ", isPEP=" + isPEP + ", isHNI=" + isHNI + ", addr1=" + addr1 + ", addr2=" + addr2
                + ", addr3=" + addr3 + ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode="
                + postalCode + ", isSTRReported=" + isSTRReported + ", isCTRReported=" + isCTRReported
                + ", industryType=" + industryType + ", customerOpenDate=" + customerOpenDate + ", binNo=" + binNo
                + ", tinNo=" + tinNo + ", customerStatus=" + customerStatus + ", nationality=" + nationality
                + ", introducer=" + introducer + ", currentCountry=" + currentCountry + ", sourceOfFund=" + sourceOfFund
                + ", dateOfComm=" + dateOfComm + ", charityYN=" + charityYN + ", shortName=" + shortName
                + ", employersName=" + employersName + ", typeOfBusiness=" + typeOfBusiness + ", countryOfReg="
                + countryOfReg + ", corpregNo=" + corpregNo + ", dateOfReg=" + dateOfReg + ", phoneNo=" + phoneNo
                + ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", annTurnover=" + annTurnover + ", spouseName="
                + spouseName + ", isSelfEmployedYN=" + isSelfEmployedYN + ", resiVisaYN=" + resiVisaYN + ", status="
                + status + ", reason=" + reason + ", accountLogId=" + accountLogId + "]";
    }
    
}
