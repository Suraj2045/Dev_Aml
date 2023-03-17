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
@Table(name = "City_Master")
public class CityMst  extends BaseEntity{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5876740242989729079L;

	@Column(name = "CITY_CODE", length = 20, nullable = false)
	private String cityCode="";
	
	@Column(name = "CITY_NAME", length = 20, nullable = false)
	private String cityName="";
	
	@Column(name = "STATE_CODE", length = 20, nullable = false)
	private String stateCode="";
	
	@Column(name = "COUNTRY_CODE", length = 20, nullable = false)
	private String countryCode="";

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	
	
	
}
