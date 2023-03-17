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
@Table(name = "baserules")
public class BaseRules extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8671382149131819434L;
	private String scenarioDescription;
	private String type;
	private String customer;
	private String sevrity;
	private String status;
	
	public String getScenarioDescription() {
		return scenarioDescription;
	}
	public void setScenarioDescription(String scenarioDescription) {
		this.scenarioDescription = scenarioDescription;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
		return "BaseRules [scenarioDescription=" + scenarioDescription + ", type=" + type + ", customer=" + customer
				+ ", sevrity=" + sevrity + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
}
