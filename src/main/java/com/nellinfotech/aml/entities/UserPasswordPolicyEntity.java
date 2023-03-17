package com.nellinfotech.aml.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

/**
 * @author ASHWIN
 */

@Entity
@Scope(value = "prototype")
@Table(name = "USERPASSWORDPOLICY")
public class UserPasswordPolicyEntity extends BaseEntity {
    
    private static final long serialVersionUID = -1636476387006228019L;
    
    @Column(name = "MINPWDLEN", length = 2, nullable = false)
    private Integer minPassLength;
    
    @Column(name = "MAXPWDLEN", length = 3, nullable = false)
    private Integer maxPassLength;
    
    @Column(name = "MINALPHA", length = 2, nullable = true)
    private Integer minAlpha;
    
    @Column(name = "MINDIGIT", length = 2, nullable = true)
    private Integer minDigit;
    
    @Column(name = "MINSPECIALCHAR", length = 2, nullable = true)
    private Integer minSpecialChar;
    
    @Column(name = "PWDEXIPRYDAYS", length = 3, nullable = false)
    private Integer pwdExpDays;
    
    @Column(name = "ENFORE_PWD_HISTORY_POLICY", length = 1, nullable = true)
    private String enforcePassHistPolicy = "N";
    
    @Column(name = "PWDUSEDHISTORYCOUNT", length = 2, nullable = true)
    private Integer passUsedHistCount = 0;
    
    @Column(name = "BADLOGINCOUNT", length = 2, nullable = false)
    private Integer badLoginCount = 0;
    
    @Column(name = "TWO_FACTOR_AUTH", length = 1, nullable = true)
    private String twoFactorAuth = "N";
    
    @Column(name = "MOBILE_OTP_AUTHENTICATION", length = 1, nullable = true)
    private String mobOtpAuth = "N";
    
    @Column(name = "BIOMETRIC_AUTHENTICATION", length = 1, nullable = true)
    private String biometricAuth = "N";
    
    @Column(name = "EMPCODE_AUTHENTICATION", length = 1, nullable = true)
    private String empCodeAuth = "N";
    
    @Column(name = "EMAIL_AUTHENTICATION", length = 1, nullable = true)
    private String emailAuth = "N";
    
    @Column(name = "CONCURRENT_LOGIN", length = 1, nullable = true)
    private String concurrentLogin = "N";
    
    @Column(name = "IDLE_LOGIN_DAYS_ALLOWED", length = 1, nullable = true)
    private String idealLoginDaysAllowed = "N";
    
    @Column(name = "IDLE_LOGIN_DAYS", length = 3, nullable = true)
    private Integer idealLoginDays;
    
    public Integer getMinPassLength() {
        return minPassLength;
    }
    
    public void setMinPassLength(Integer minPassLength) {
        this.minPassLength = minPassLength;
    }
    
    public Integer getMaxPassLength() {
        return maxPassLength;
    }
    
    public void setMaxPassLength(Integer maxPassLength) {
        this.maxPassLength = maxPassLength;
    }
    
    public Integer getMinAlpha() {
        return minAlpha;
    }
    
    public void setMinAlpha(Integer minAlpha) {
        this.minAlpha = minAlpha;
    }
    
    public Integer getMinDigit() {
        return minDigit;
    }
    
    public void setMinDigit(Integer minDigit) {
        this.minDigit = minDigit;
    }
    
    public Integer getMinSpecialChar() {
        return minSpecialChar;
    }
    
    public void setMinSpecialChar(Integer minSpecialChar) {
        this.minSpecialChar = minSpecialChar;
    }
    
    public Integer getPwdExpDays() {
        return pwdExpDays;
    }
    
    public void setPwdExpDays(Integer pwdExpDays) {
        this.pwdExpDays = pwdExpDays;
    }
    
    public String getEnforcePassHistPolicy() {
        return enforcePassHistPolicy;
    }
    
    public void setEnforcePassHistPolicy(String enforcePassHistPolicy) {
        this.enforcePassHistPolicy = enforcePassHistPolicy;
    }
    
    public Integer getPassUsedHistCount() {
        return passUsedHistCount;
    }
    
    public void setPassUsedHistCount(Integer passUsedHistCount) {
        this.passUsedHistCount = passUsedHistCount;
    }
    
    public Integer getBadLoginCount() {
        return badLoginCount;
    }
    
    public void setBadLoginCount(Integer badLoginCount) {
        this.badLoginCount = badLoginCount;
    }
    
    public String getTwoFactorAuth() {
        return twoFactorAuth;
    }
    
    public void setTwoFactorAuth(String twoFactorAuth) {
        this.twoFactorAuth = twoFactorAuth;
    }
    
    public String getMobOtpAuth() {
        return mobOtpAuth;
    }
    
    public void setMobOtpAuth(String mobOtpAuth) {
        this.mobOtpAuth = mobOtpAuth;
    }
    
    public String getBiometricAuth() {
        return biometricAuth;
    }
    
    public void setBiometricAuth(String biometricAuth) {
        this.biometricAuth = biometricAuth;
    }
    
    public String getEmpCodeAuth() {
        return empCodeAuth;
    }
    
    public void setEmpCodeAuth(String empCodeAuth) {
        this.empCodeAuth = empCodeAuth;
    }
    
    public String getEmailAuth() {
        return emailAuth;
    }
    
    public void setEmailAuth(String emailAuth) {
        this.emailAuth = emailAuth;
    }
    
    public String getConcurrentLogin() {
        return concurrentLogin;
    }
    
    public void setConcurrentLogin(String concurrentLogin) {
        this.concurrentLogin = concurrentLogin;
    }
    
    public String getIdealLoginDaysAllowed() {
        return idealLoginDaysAllowed;
    }
    
    public void setIdealLoginDaysAllowed(String idealLoginDaysAllowed) {
        this.idealLoginDaysAllowed = idealLoginDaysAllowed;
    }
    
    public Integer getIdealLoginDays() {
        return idealLoginDays;
    }
    
    public void setIdealLoginDays(Integer idealLoginDays) {
        this.idealLoginDays = idealLoginDays;
    }
    
    @Override
    public String toString() {
        return "UserPasswordPolicyEntity [minPassLength=" + minPassLength + ", maxPassLength=" + maxPassLength
                + ", minAlpha=" + minAlpha + ", minDigit=" + minDigit + ", minSpecialChar=" + minSpecialChar
                + ", pwdExpDays=" + pwdExpDays + ", enforcePassHistPolicy=" + enforcePassHistPolicy
                + ", passUsedHistCount=" + passUsedHistCount + ", badLoginCount=" + badLoginCount + ", twoFactorAuth="
                + twoFactorAuth + ", mobOtpAuth=" + mobOtpAuth + ", biometricAuth=" + biometricAuth + ", empCodeAuth="
                + empCodeAuth + ", emailAuth=" + emailAuth + ", concurrentLogin=" + concurrentLogin
                + ", idealLoginDaysAllowed=" + idealLoginDaysAllowed + ", idealLoginDays=" + idealLoginDays + "]";
    }
    
}
