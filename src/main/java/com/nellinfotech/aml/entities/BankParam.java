package com.nellinfotech.aml.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "Bank_Param")
public class BankParam extends BaseEntity {
    
    private static final long serialVersionUID = 8617413977485559696L;
    
    // dd-MM-yyyy, yyyy-MM-dd
    @Column(name = "DAFAULT_DATE_FORMAT", length = 20, nullable = false)
    private String defaultDateFormat = "";
    
    // Lakhs, Millions
    @Column(name = "DAFAULT_AMOUNT_FORMAT", length = 20, nullable = false)
    private String defaultAmtFormat = "";
    
    @Column(name = "DECIMAL_SEPRATOR", length = 2, nullable = false)
    private String decimalSep = ".";
    
    @Column(name = "DIGIT_SEPRATOR", length = 2, nullable = false)
    private String digitSep = ",";
    
    @Column(name = "DAFAULT_CURRENCY", length = 20, nullable = false)
    private String defaultCurrency = "";
    
    @Column(name = "DAFAULT_LANGUAGE", length = 3, nullable = false)
    private String defaultLanguage = "";
    
    @Column(name = "DAFAULT_HOME", length = 50, nullable = false)
    private String defaultHome = "";
    
    @Column(name = "CUSTOMER_DAYS", length = 20, nullable = false)
    private String customerDays = "";
    
    public String getDefaultDateFormat() {
        return defaultDateFormat;
    }
    
    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
    }
    
    public String getDefaultAmtFormat() {
        return defaultAmtFormat;
    }
    
    public void setDefaultAmtFormat(String defaultAmtFormat) {
        this.defaultAmtFormat = defaultAmtFormat;
    }
    
    public String getDecimalSep() {
        return decimalSep;
    }
    
    public void setDecimalSep(String decimalSep) {
        this.decimalSep = decimalSep;
    }
    
    public String getDigitSep() {
        return digitSep;
    }
    
    public void setDigitSep(String digitSep) {
        this.digitSep = digitSep;
    }
    
    public String getDefaultCurrency() {
        return defaultCurrency;
    }
    
    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
    
    public String getDefaultLanguage() {
        return defaultLanguage;
    }
    
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
    
    public String getDefaultHome() {
        return defaultHome;
    }
    
    public void setDefaultHome(String defaultHome) {
        this.defaultHome = defaultHome;
    }
    
    public String getCustomerDays() {
        return customerDays;
    }
    
    public void setCustomerDays(String customerDays) {
        this.customerDays = customerDays;
    }
    
    @Override
    public String toString() {
        return "BankParam [defaultDateFormat=" + defaultDateFormat + ", defaultAmtFormat=" + defaultAmtFormat
                + ", decimalSep=" + decimalSep + ", digitSep=" + digitSep + ", defaultCurrency=" + defaultCurrency
                + ", defaultLanguage=" + defaultLanguage + ", defaultHome=" + defaultHome + ", customerDays="
                + customerDays + "]";
    }
    
}
