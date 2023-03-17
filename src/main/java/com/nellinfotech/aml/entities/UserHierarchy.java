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
@Table(name = "User_Hierarchy")
public class UserHierarchy extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4818081717047783044L;
    
    @Column(name = "user_Id", length = 20, nullable = false)
    private String userId = "";
    
    @Column(name = "reportedUser_Id", length = 20)
    private String reportedUserId = "";
    
    @Column(name = "GROUP_NAME", length = 50)
    private String groupName;
    
    public String getUserId() {
        return userId;
    }
    
    public String getReportedUserId() {
        return reportedUserId;
    }
    
    public void setReportedUserId(String reportedUserId) {
        this.reportedUserId = reportedUserId;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        return "UserHierarchy [userId=" + userId + ", reportedUserId=" + reportedUserId + ", groupName=" + groupName
                + "]";
    }
    
}
