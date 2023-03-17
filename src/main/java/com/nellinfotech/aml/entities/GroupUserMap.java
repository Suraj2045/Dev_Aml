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
@Table(name = "GROUP_USER_MAP")
public class GroupUserMap extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4289608679249521989L;
    
    @Column(name = "USER_ID", length = 20, nullable = false)
    private String userId = "";
    
    @Column(name = "GROUP_NAME", length = 50, nullable = false)
    private String groupName;
    
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
    
    @Override
    public String toString() {
        return "GroupUserMap [userId=" + userId + ", groupName=" + groupName + "]";
    }
    
}
