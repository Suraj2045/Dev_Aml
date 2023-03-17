package com.nellinfotech.aml.model;

import com.nellinfotech.aml.entities.BaseEntity;

public class RoleDetails extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3238474116093664597L;
    private String roleCode;
    private String menuCode = "";
    private String menuName = "";
    private String menuDesc = "";
    
    private String menuFunctionality = "";
    private String menuURL = "";
    private String isVisible;
    
    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
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
    
    public String getIsVisible() {
        return isVisible;
    }
    
    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }
    
    public String getMenuDesc() {
        return menuDesc;
    }
    
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }
}
