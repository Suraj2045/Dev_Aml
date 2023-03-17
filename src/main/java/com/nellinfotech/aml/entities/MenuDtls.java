package com.nellinfotech.aml.entities;

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
@Table(name = "MENU_DETAILS")
public class MenuDtls extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5285444808971304592L;
    private String menuCode = "";
    private String menuId = "";
    private String menuName = "";
    private String menuFeature = "";
    private String menuFunctionality = "";
    private String menuURL = "";
    
    public String getMenuCode() {
        return menuCode;
    }
    
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    
    public String getMenuName() {
        return menuName;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    public String getMenuFeature() {
        return menuFeature;
    }
    
    public void setMenuFeature(String menuFeature) {
        this.menuFeature = menuFeature;
    }
    
    public String getMenuFunctionality() {
        return menuFunctionality;
    }
    
    public void setMenuFunctionality(String menuFunctionality) {
        this.menuFunctionality = menuFunctionality;
    }
    
    public String getMenuURL() {
        return menuURL;
    }
    
    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }
    
    public String getMenuId() {
        return menuId;
    }
    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    @Override
    public String toString() {
        return "MenuDtls [menuCode=" + menuCode + ", menuName=" + menuName + ", menuFeature=" + menuFeature
                + ", menuFunctionality=" + menuFunctionality + ", menuURL=" + menuURL + "]";
    }
    
}
