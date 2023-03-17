package com.nellinfotech.aml.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "USER_MASTER")
public class UserMst extends BaseEntity {
    
    private static final long serialVersionUID = -8982457617782336330L;
    
    @Column(name = "USER_ID", length = 20, nullable = false)
    private String userId = "";
    
    @Column(name = "FIRST_NAME", length = 20, nullable = false)
    private String firstName = "";
    
    @Column(name = "LAST_NAME", length = 20, nullable = false)
    private String lastName = "";
    
    //    @Column(name = "BASE_BRANCH", length = 20, nullable = false)
    //    private String baseBr = "";
    
    @Column(name = "BASE_ROLE_CODE", length = 20, nullable = false)
    private String baseRoleCode = "";
    
    @Column(name = "USER_PASSWORD", length = 500, nullable = false)
    private String pwd = "";
    
    @Column(name = "USER_BADLOGINCOUNT", length = 5, nullable = false)
    private Integer badLoginCount = 0;
    
    @Column(name = "ISUSER_LOGGEDIN", length = 5, nullable = false)
    private Integer isUserLoggedIn = 0;
    
    @Column(name = "USER_ISCONCURRENTLOGINALLOWED", length = 5, nullable = false)
    private Integer isConcurrentLoginAllowed = 0;
    
    @Column(name = "ISUSER_LOCKED", length = 5, nullable = false)
    private Integer isUserLocked = 0;
    
    @Column(name = "ISLOGINSUSPENDED", length = 5, nullable = false)
    private Integer isLoginSuspended = 0;
    
    @Column(name = "PWDCHANGENEXTLOGIN", length = 5, nullable = false)
    private Integer pwdChangeNextLogin = 0;
    
    @Column(name = "PWDEXPIRYDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Kolkata", locale = "en-IN")
    private Date pwdExpiryDate;
    
    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email = "";
    
    @Column(name = "MOBILE_NO", length = 13, nullable = false)
    private String mobileNo = "";
    
    @Column(name = "DIVISION", length = 20, nullable = false)
    private String division = "";
    
    @Column(name = "ROLE_RISK_SEVERITY", length = 50, nullable = false)
    private String roleRiskSeverity = "";
    
    //    @Column(name = "DEPARTMENT", length = 13, nullable = false)
    //    private String department = "";
    
    @Column(name = "STATUS", length = 13, nullable = false)
    private String status = "";
    
    @Column(name = "BRANCH", length = 13, nullable = false)
    private String branch = "";
    
    //    @Column(name = "BANK_NAME", length = 13, nullable = false)
    //    private String bankName = "";
    //    
    //    @Column(name = "ALERT_CATEGORY", length = 13, nullable = false)
    //    private String alertCategory = "";
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    //    public String getBaseBr() {
    //        return baseBr;
    //    }
    //    
    //    public void setBaseBr(String baseBr) {
    //        this.baseBr = baseBr;
    //    }
    
    public String getBaseRoleCode() {
        return baseRoleCode;
    }
    
    public void setBaseRoleCode(String baseRoleCode) {
        this.baseRoleCode = baseRoleCode;
    }
    
    public String getPwd() {
        return pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public Integer getBadLoginCount() {
        return badLoginCount;
    }
    
    public void setBadLoginCount(Integer badLoginCount) {
        this.badLoginCount = badLoginCount;
    }
    
    public Integer getIsUserLoggedIn() {
        return isUserLoggedIn;
    }
    
    public void setIsUserLoggedIn(Integer isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }
    
    public Integer getIsConcurrentLoginAllowed() {
        return isConcurrentLoginAllowed;
    }
    
    public void setIsConcurrentLoginAllowed(Integer isConcurrentLoginAllowed) {
        this.isConcurrentLoginAllowed = isConcurrentLoginAllowed;
    }
    
    public Integer getIsUserLocked() {
        return isUserLocked;
    }
    
    public void setIsUserLocked(Integer isUserLocked) {
        this.isUserLocked = isUserLocked;
    }
    
    public Integer getIsLoginSuspended() {
        return isLoginSuspended;
    }
    
    public void setIsLoginSuspended(Integer isLoginSuspended) {
        this.isLoginSuspended = isLoginSuspended;
    }
    
    public Integer getPwdChangeNextLogin() {
        return pwdChangeNextLogin;
    }
    
    public void setPwdChangeNextLogin(Integer pwdChangeNextLogin) {
        this.pwdChangeNextLogin = pwdChangeNextLogin;
    }
    
    public Date getPwdExpiryDate() {
        return pwdExpiryDate;
    }
    
    public void setPwdExpiryDate(Date pwdExpiryDate) {
        this.pwdExpiryDate = pwdExpiryDate;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public String getDivision() {
        return division;
    }
    
    public void setDivision(String division) {
        this.division = division;
    }
    
    public String getRoleRiskSeverity() {
        return roleRiskSeverity;
    }
    
    public void setRoleRiskSeverity(String roleRiskSeverity) {
        this.roleRiskSeverity = roleRiskSeverity;
    }
    
    //    public String getDepartment() {
    //        return department;
    //    }
    //    
    //    public void setDepartment(String department) {
    //        this.department = department;
    //    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getBranch() {
        return branch;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    //    public String getBankName() {
    //        return bankName;
    //    }
    //    
    //    public void setBankName(String bankName) {
    //        this.bankName = bankName;
    //    }
    //    
    //    public String getAlertCategory() {
    //        return alertCategory;
    //    }
    //    
    //    public void setAlertCategory(String alertCategory) {
    //        this.alertCategory = alertCategory;
    //    }
    
}