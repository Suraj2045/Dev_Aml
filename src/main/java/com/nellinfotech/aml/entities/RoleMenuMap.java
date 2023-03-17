package com.nellinfotech.aml.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "ROLE_MENU_MAP")
public class RoleMenuMap extends BaseEntity {
    
    private static final long serialVersionUID = -5162942922768364624L;
    
    @Column(name = "ROLE_CODE", length = 20, nullable = false)
    private String roleCode = "";
    
    @Column(name = "MENU_CODE", length = 20, nullable = false)
    private String menuCode = "";
    
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
    
}