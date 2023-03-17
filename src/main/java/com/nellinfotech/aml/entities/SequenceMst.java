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
@Table(name = "SEQUENCE_MASTER")
public class SequenceMst extends BaseEntity {
    
    private static final long serialVersionUID = -8542350264661659274L;
    
    @Column(name = "SEQ_CODE", length = 20, nullable = false)
    private String seqCode = "";
    
    @Column(name = "START_NO", length = 20, nullable = false)
    private Integer startNo = 1;
    
    @Column(name = "INCR_BY", length = 20, nullable = false)
    private Integer incrBy = 1;
    
    @Column(name = "PREFIX", length = 20, nullable = false)
    private String prefix = "";
    
    @Column(name = "SUFFIX", length = 20, nullable = false)
    private String Suffix = "";
    
    public String getSeqCode() {
        return seqCode;
    }
    
    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode;
    }
    
    public Integer getStartNo() {
        return startNo;
    }
    
    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }
    
    public Integer getIncrBy() {
        return incrBy;
    }
    
    public void setIncrBy(Integer incrBy) {
        this.incrBy = incrBy;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    public String getSuffix() {
        return Suffix;
    }
    
    public void setSuffix(String suffix) {
        Suffix = suffix;
    }
    
}