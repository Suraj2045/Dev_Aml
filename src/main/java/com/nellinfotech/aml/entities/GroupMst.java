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
@Table(name = "Group_Master")
public class GroupMst extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Column(name = "GROUP_NAME", length = 50, nullable = false)
    private String groupName;
    
    @Column(name = "GROUP_DESCRIPTION", length = 150, nullable = false)
    private String groupDescription;
    
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
    
    @Override
    public String toString() {
        return "GroupMst [groupName=" + groupName + ", groupDescription=" + groupDescription + "]";
    }
    
}
