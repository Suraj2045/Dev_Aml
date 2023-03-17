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
@Table(name = "Currency_Master")
public class CurrencyMst extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726665594691645323L;
	@Column(name="Currency_Code", length = 20 , nullable = false)
	private String currencyCode;  	
	
	@Column(name="Currency_Name", length = 20 , nullable = false)
	private String currencyName;
	
	@Column(name="Major_Unit", length = 20 , nullable = false)
	private String majorUnit;
	
	@Column(name="Minor_Unit", length = 20 , nullable = false)
	private String minorUnit;
	
	@Column(name="Conversion_Factor", length = 20 , nullable = false)
	private Integer conversionFactor;
	
	@Column(name="Decimal_Unit", length = 20 , nullable = false)
	private String decimalUnit;
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getMajorUnit() {
		return majorUnit;
	}
	public void setMajorUnit(String majorUnit) {
		this.majorUnit = majorUnit;
	}
	public String getMinorUnit() {
		return minorUnit;
	}
	public void setMinorUnit(String minorUnit) {
		this.minorUnit = minorUnit;
	}
	public String getDecimalUnit() {
		return decimalUnit;
	}
	public void setDecimalUnit(String decimalUnit) {
		this.decimalUnit = decimalUnit;
	}
	public Integer getConversionFactor() {
		return conversionFactor;
	}
	public void setConversionFactor(Integer conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	
	
	

}
