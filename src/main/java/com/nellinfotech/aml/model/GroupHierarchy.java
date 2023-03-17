package com.nellinfotech.aml.model;

import com.nellinfotech.aml.entities.BaseEntity;

public class GroupHierarchy extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -4535899126881473040L;
    private String userId = "";
    private String groupName;
    private String groupDescription;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getGroupDescription() {
        return groupDescription;
    }
    
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
    
}
