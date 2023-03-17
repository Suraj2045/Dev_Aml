package com.nellinfotech.aml.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "FileUploadLog")
public class FileUploadLog extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1459113275237939049L;
    
    @Column(name = "FILE_NAME", nullable = false, length = 25)
    String fileName = "";
    
    @Column(name = "PASS_COUNT", nullable = false, length = 25)
    Integer passCount;
    
    @Column(name = "FAIL_COUNT", nullable = false, length = 25)
    Integer failCount;
    
    @Column(name = "UPLOAD_DATETIME", nullable = true)
    
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date uploadDateTime = Calendar.getInstance().getTime();
    
    @Column(name = "ACCOUNT_LOG_ID", nullable = true, length = 25)
    private long accountLogId;
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Date getUploadDateTime() {
        return uploadDateTime;
    }
    
    public Integer getPassCount() {
        return passCount;
    }
    
    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }
    
    public Integer getFailCount() {
        return failCount;
    }
    
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }
    
    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
    
    public long getAccountLogId() {
        return accountLogId;
    }
    
    public void setAccountLogId(long accountLogId) {
        this.accountLogId = accountLogId;
    }
    
    @Override
    public String toString() {
        return "FileUploadLog [fileName=" + fileName + ", passCount=" + passCount + ", failCount=" + failCount
                + ", uploadDateTime=" + uploadDateTime + ", accountLogId=" + accountLogId + "]";
    }
    
}
