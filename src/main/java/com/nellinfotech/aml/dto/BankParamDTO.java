package com.nellinfotech.aml.dto;

public class BankParamDTO {
	
	private int id;
	private String defaultDateFormat;
	private String defaultAmtFormat;
	private String decimalSep;
	private String digitSep;
	private String defaultCurrency;
	private String defaultLanguage;
	private String defaultHome;
	private String customerDays;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}

	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}

	public String getDefaultAmtFormat() {
		return defaultAmtFormat;
	}

	public void setDefaultAmtFormat(String defaultAmtFormat) {
		this.defaultAmtFormat = defaultAmtFormat;
	}

	public String getDecimalSep() {
		return decimalSep;
	}

	public void setDecimalSep(String decimalSep) {
		this.decimalSep = decimalSep;
	}

	public String getDigitSep() {
		return digitSep;
	}

	public void setDigitSep(String digitSep) {
		this.digitSep = digitSep;
	}

	public String getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getDefaultHome() {
		return defaultHome;
	}

	public void setDefaultHome(String defaultHome) {
		this.defaultHome = defaultHome;
	}

	public String getCustomerDays() {
		return customerDays;
	}

	public void setCustomerDays(String customerDays) {
		this.customerDays = customerDays;
	}

	@Override
	public String toString() {
		return "BankParamDTO [id=" + id + ", defaultDateFormat=" + defaultDateFormat + ", defaultAmtFormat="
				+ defaultAmtFormat + ", decimalSep=" + decimalSep + ", digitSep=" + digitSep + ", defaultCurrency="
				+ defaultCurrency + ", defaultLanguage=" + defaultLanguage + ", defaultHome=" + defaultHome
				+ ", customerDays=" + customerDays + "]";
	}

	public BankParamDTO(int id, String defaultDateFormat, String defaultAmtFormat, String decimalSep, String digitSep,
			String defaultCurrency, String defaultLanguage, String defaultHome, String customerDays) {
	
		this.id = id;
		this.defaultDateFormat = defaultDateFormat;
		this.defaultAmtFormat = defaultAmtFormat;
		this.decimalSep = decimalSep;
		this.digitSep = digitSep;
		this.defaultCurrency = defaultCurrency;
		this.defaultLanguage = defaultLanguage;
		this.defaultHome = defaultHome;
		this.customerDays = customerDays;
	}

	public BankParamDTO() {
		super();
	}

}
