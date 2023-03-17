package com.nellinfotech.aml.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ASHWIN
 */

@Entity
@Scope(value = "prototype")
@Table(name = "USER_PASSWORD_HISTORY")
public class UserPasswordHistoryEntity extends BaseEntity {
    
    private static final long serialVersionUID = 7288072460094334721L;
    
    @Column(name = "USER_ID", length = 20)
    private int userId;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "PASSWOR_CHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Kolkata", locale = "en-IN")
    private Date passwordChangeDate;
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }
    
    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }
    
}
