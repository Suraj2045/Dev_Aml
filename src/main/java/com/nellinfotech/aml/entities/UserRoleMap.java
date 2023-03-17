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
@Table(name = "USER_ROLE_MAP")
public class UserRoleMap extends BaseEntity {
    
    private static final long serialVersionUID = 3612863535575321898L;
    
    @Column(name = "USER_ID", length = 20, nullable = false)
    private String userId = "";
    
    @Column(name = "ROLE_CODE", length = 20, nullable = false)
    private String roleCode = "";
    
    @Column(name = "BRANCH_CODE", length = 20, nullable = false)
    private String brCode = "";
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getBrCode() {
        return brCode;
    }
    
    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }
    
}