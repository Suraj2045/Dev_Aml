package com.nellinfotech.aml.entities;

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
@Table(name = "uirule")
public class UIRule extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6246347609194198634L;

	private String ui_Made_Rule;
	private Long baseRuleId;
	private String frequence;
	private String sevrity;
	private String status;
	
	
	
	public String getUi_Made_Rule() {
		return ui_Made_Rule;
	}
	public void setUi_Made_Rule(String ui_Made_Rule) {
		this.ui_Made_Rule = ui_Made_Rule;
	}
	public Long getBaseRuleId() {
		return baseRuleId;
	}
	public void setBaseRuleId(Long baseRuleId) {
		this.baseRuleId = baseRuleId;
	}
	
	
	public String getFrequence() {
		return frequence;
	}
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}
	public String getSevrity() {
		return sevrity;
	}
	public void setSevrity(String sevrity) {
		this.sevrity = sevrity;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UIRule [ui_Made_Rule=" + ui_Made_Rule + ", baseRuleId=" + baseRuleId + ", frequence=" + frequence
				+ ", sevrity=" + sevrity + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	
}
