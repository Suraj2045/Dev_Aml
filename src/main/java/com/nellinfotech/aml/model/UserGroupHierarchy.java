package com.nellinfotech.aml.model;

import com.nellinfotech.aml.entities.BaseEntity;

public class UserGroupHierarchy extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5545608451576496344L;
    private String userId = "";
    private String userName = "";
    private String userRole = "";
    private String userRoleName = "";
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getUserRoleName() {
        return userRoleName;
    }
    
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
    
    @Override
    public String toString() {
        return "UserGroupHierarchy [userId=" + userId + ", userName=" + userName + ", userRole=" + userRole + "]";
    }
    
}
