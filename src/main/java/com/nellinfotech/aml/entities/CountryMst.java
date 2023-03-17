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
@Table(name = "Country_Master")
public class CountryMst extends BaseEntity{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5315853383389158299L;
	
	@Column(name = "COUNTRY_CODE", length = 20, nullable = false)
	private String countryCode="";
	
	@Column(name = "COUNTRY_NAME", length = 20, nullable = false)
	private String countryName="";

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
}
