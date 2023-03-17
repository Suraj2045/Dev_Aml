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
@Table(name = "BANK_MASTER")
public class BankMst extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5499819097045934612L;
    
    @Column(name = "BANK_NAME", length = 250, nullable = false)
    private String bankName = "";
    
    @Column(name = "BANK_LOGO", length = 250, nullable = false)
    private String bankLogo = "";
    
    @Column(name = "BANK_DEAFULT_LANGUAGE", length = 3, nullable = false)
    private String bankDefLang = "";
    
    @Column(name = "BANK_IFSC", length = 20, nullable = false)
    private String bankIfsc = "";
    
    @Column(name = "BANK_ADDRESS", length = 250, nullable = false)
    private String bankAddress;
    
    @Column(name = "BANK_CONTACT_NO", length = 20, nullable = false)
    private Long bankContactNo;
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getBankLogo() {
        return bankLogo;
    }
    
    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
    
    public String getBankDefLang() {
        return bankDefLang;
    }
    
    public void setBankDefLang(String bankDefLang) {
        this.bankDefLang = bankDefLang;
    }
    
    public String getBankIfsc() {
        return bankIfsc;
    }
    
    public void setBankIfsc(String bankIfsc) {
        this.bankIfsc = bankIfsc;
    }
    
    public String getBankAddress() {
        return bankAddress;
    }
    
    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }
    
    public Long getBankContactNo() {
        return bankContactNo;
    }
    
    public void setBankContactNo(Long bankContactNo) {
        this.bankContactNo = bankContactNo;
    }
    
}
