package com.nellinfotech.aml.dto;

public class BranchPramDTO {
	private int id;
	private String deafult_date_format;
	private String deafult_amount_format;
	private String decimal_seprator;
	private String digit_seprator;
	private String deafult_language;
	private String branch_code;
	private String deafult_number_format;
	
	public String getDeafult_date_format() {
		return deafult_date_format;
	}
	public void setDeafult_date_format(String deafult_date_format) {
		this.deafult_date_format = deafult_date_format;
	}
	public String getDeafult_amount_format() {
		return deafult_amount_format;
	}
	public void setDeafult_amount_format(String deafult_amount_format) {
		this.deafult_amount_format = deafult_amount_format;
	}
	public String getDecimal_seprator() {
		return decimal_seprator;
	}
	public void setDecimal_seprator(String decimal_seprator) {
		this.decimal_seprator = decimal_seprator;
	}
	public String getDigit_seprator() {
		return digit_seprator;
	}
	public void setDigit_seprator(String digit_seprator) {
		this.digit_seprator = digit_seprator;
	}
	public String getDeafult_language() {
		return deafult_language;
	}
	public void setDeafult_language(String deafult_language) {
		this.deafult_language = deafult_language;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getDeafult_number_format() {
		return deafult_number_format;
	}
	public void setDeafult_number_format(String deafult_number_format) {
		this.deafult_number_format = deafult_number_format;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public BranchPramDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BranchPramDTO(int id, String deafult_date_format, String deafult_amount_format, String decimal_seprator,
			String digit_seprator, String deafult_language, String branch_code, String deafult_number_format) {
		super();
		this.id = id;
		this.deafult_date_format = deafult_date_format;
		this.deafult_amount_format = deafult_amount_format;
		this.decimal_seprator = decimal_seprator;
		this.digit_seprator = digit_seprator;
		this.deafult_language = deafult_language;
		this.branch_code = branch_code;
		this.deafult_number_format = deafult_number_format;
	}
	@Override
	public String toString() {
		return "BranchPramDTO [id=" + id + ", deafult_date_format=" + deafult_date_format + ", deafult_amount_format="
				+ deafult_amount_format + ", decimal_seprator=" + decimal_seprator + ", digit_seprator="
				+ digit_seprator + ", deafult_language=" + deafult_language + ", branch_code=" + branch_code
				+ ", deafult_number_format=" + deafult_number_format + "]";
	}
	

}
