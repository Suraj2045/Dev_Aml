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
@Table(name = "State_Master")
public class StateMst extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8619145942377725878L;

	@Column(name = "STATE_CODE", length = 20, nullable = false)
	private String stateCode="";
	
	@Column(name = "STATE_NAME", length = 20, nullable = false)
	private String stateName="";
	
	@Column(name = "COUNTRY_CODE", length = 20, nullable = false)
	private String countryCode="";

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	
	
	
	
}
