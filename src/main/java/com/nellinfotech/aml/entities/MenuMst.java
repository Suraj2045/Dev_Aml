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
@Table(name = "MENU_MASTER")
public class MenuMst extends BaseEntity {
    
    private static final long serialVersionUID = -6921406192741128839L;
    
    @Column(name = "MENU_CODE", length = 20, nullable = false)
    private String menuCode = "USER";
    
    @Column(name = "MENU_DESC", length = 250, nullable = false)
    private String MenuDesc = "";
    
    @Column(name = "MENU_URL", length = 250, nullable = false)
    private String MenuURL = "";
    
    public String getMenuCode() {
        return menuCode;
    }
    
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    
    public String getMenuDesc() {
        return MenuDesc;
    }
    
    public void setMenuDesc(String menuDesc) {
        MenuDesc = menuDesc;
    }
    
    public String getMenuURL() {
        return MenuURL;
    }
    
    public void setMenuURL(String menuURL) {
        MenuURL = menuURL;
    }
    
}