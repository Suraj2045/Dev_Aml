package com.nellinfotech.aml.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = "ProfileRelationship")
public class ProfileRelationships implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8126725843104063658L;
    /**
     * 
     */
    // private static final long serialVersionUID = 2218296059912640620L;
    
    @XmlAttribute(name = "From-ProfileID")
    private Integer From_ProfileID;
    @XmlAttribute(name = "To-ProfileID")
    private Integer To_ProfileID;
    @XmlAttribute(name = "RelationTypeID")
    private Integer RelationTypeID;
    @XmlAttribute(name = "RelationQualityID")
    private Integer RelationQualityID;
    @XmlAttribute(name = "Former")
    private boolean Former;
    @XmlAttribute(name = "SanctionsEntryID")
    private Integer SanctionsEntryID;
    
    public Integer getFrom_ProfileID() {
        return From_ProfileID;
    }
    
    public void setFrom_ProfileID(Integer from_ProfileID) {
        From_ProfileID = from_ProfileID;
    }
    
    public Integer getTo_ProfileID() {
        return To_ProfileID;
    }
    
    public void setTo_ProfileID(Integer to_ProfileID) {
        To_ProfileID = to_ProfileID;
    }
    
    public Integer getRelationTypeID() {
        return RelationTypeID;
    }
    
    public void setRelationTypeID(Integer relationTypeID) {
        RelationTypeID = relationTypeID;
    }
    
    public Integer getRelationQualityID() {
        return RelationQualityID;
    }
    
    public void setRelationQualityID(Integer relationQualityID) {
        RelationQualityID = relationQualityID;
    }
    
    public boolean isFormer() {
        return Former;
    }
    
    public void setFormer(boolean former) {
        Former = former;
    }
    
    public Integer getSanctionsEntryID() {
        return SanctionsEntryID;
    }
    
    public void setSanctionsEntryID(Integer sanctionsEntryID) {
        SanctionsEntryID = sanctionsEntryID;
    }
    
    @Override
    public String toString() {
        return "ProfileRelationships [ From_ProfileID=" + From_ProfileID + ", To_ProfileID=" + To_ProfileID
                + ", RelationTypeID=" + RelationTypeID + ", RelationQualityID=" + RelationQualityID + ", Former="
                + Former + ", SanctionsEntryID=" + SanctionsEntryID + "]";
    }
    
}
