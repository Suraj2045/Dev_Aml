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
@Table(name = "BRANCH_PARAM")
public class BranchParam extends BaseEntity {
    
    private static final long serialVersionUID = -3175621662541271536L;
    
    @Column(name = "BRANCH_CODE", length = 20, nullable = false)
    private String branchCode = "";
    // dd-MM-yyyy, yyyy-MM-dd
    @Column(name = "DEAFULT_DATE_FORMAT", length = 20, nullable = false)
    private String defaultDateFormat = "";
    
    // Lakhs, Millions
    @Column(name = "DEAFULT_AMOUNT_FORMAT", length = 20, nullable = false)
    private String defaultAmtFormat = "";
    
    @Column(name = "DECIMAL_SEPRATOR", length = 20, nullable = false)
    private String decimalSep = ".";
    
    @Column(name = "DIGITL_SEPRATOR", length = 20, nullable = false)
    private String digitSep = ",";
    
    @Column(name = "DEAFULT_NUMBER_FORMAT", length = 20, nullable = false)
    private String deafultNumberFormat;
    
    @Column(name = "DEAFULT_LANGUAGE", length = 50, nullable = false)
    private String deafultLanguage = "en";
    
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
    
    public String getDeafultNumberFormat() {
        return deafultNumberFormat;
    }
    
    public void setDeafultNumberFormat(String deafultNumberFormat) {
        this.deafultNumberFormat = deafultNumberFormat;
    }
    
    public String getDeafultLanguage() {
        return deafultLanguage;
    }
    
    public void setDeafultLanguage(String deafultLanguage) {
        this.deafultLanguage = deafultLanguage;
    }
    
    public String getBranchCode() {
        return branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    
}
