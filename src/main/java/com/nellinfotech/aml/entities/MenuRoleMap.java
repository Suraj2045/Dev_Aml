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
@Table(name = "Menu_Role_Map")
public class MenuRoleMap extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6637278533529043042L;
    private String roleCode;
    private String menuCode;
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
    
    public String getIsVisible() {
        return isVisible;
    }
    
    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }
    
}
