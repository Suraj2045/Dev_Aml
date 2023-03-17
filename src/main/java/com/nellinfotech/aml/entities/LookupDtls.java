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
@Table(name = "LOOKUP_DETAILS")
public class LookupDtls extends BaseEntity {
    
    private static final long serialVersionUID = 300296136618972724L;
    
    @Column(name = "LOOKUP_CODE", length = 20, nullable = false)
    private String lookupCode = ""; // Gender
    
    @Column(name = "LOOKUP_VALUE_CODE", length = 20, nullable = false)
    private String lookupValueCode = "";//M,F,O
    
    @Column(name = "LOOKUP_VALUE", length = 20, nullable = false)
    private String lookupValue = ""; // Male, Female, Others
    
    @Column(name = "LANG_CODE", length = 20, nullable = false)
    private String langCode = "en";
    
    @Column(name = "LOOKUP_ALIAS", length = 20, nullable = false)
    private String lookupAlias = ""; //1,2,3
    
    public String getLookupCode() {
        return lookupCode;
    }
    
    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }
    
    public String getLookupValueCode() {
        return lookupValueCode;
    }
    
    public void setLookupValueCode(String lookupValueCode) {
        this.lookupValueCode = lookupValueCode;
    }
    
    public String getLookupValue() {
        return lookupValue;
    }
    
    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }
    
    public String getLangCode() {
        return langCode;
    }
    
    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
    
    public String getLookupAlias() {
        return lookupAlias;
    }
    
    public void setLookupAlias(String lookupAlias) {
        this.lookupAlias = lookupAlias;
    }
    
}