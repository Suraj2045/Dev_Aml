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
@Table(name = "Language_Mst")
public class LanguageMst extends BaseEntity {
    
    private static final long serialVersionUID = 3808531824949801305L;
    
    @Column(name = "LANGUAGE_CODE", length = 20, nullable = false)
    private String langCode = "";
    
    @Column(name = "LANGUAGE_DESCRIPTION", length = 250, nullable = false)
    private String langDesc = "";
    
    public String getLangCode() {
        return langCode;
    }
    
    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
    
    public String getLangDesc() {
        return langDesc;
    }
    
    public void setLangDesc(String langDesc) {
        this.langDesc = langDesc;
    }
    
}
