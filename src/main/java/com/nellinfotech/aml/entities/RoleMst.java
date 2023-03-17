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
@Table(name = "ROLE_MASTER")
public class RoleMst extends BaseEntity {
    
    private static final long serialVersionUID = -7374091437485938494L;
    
    @Column(name = "ROLE_CODE", length = 20, nullable = false)
    private String roleCode = "";
    
    @Column(name = "ROLE_DESC", length = 250, nullable = false)
    private String roleDesc = "";
    
    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getRoleDesc() {
        return roleDesc;
    }
    
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    
}