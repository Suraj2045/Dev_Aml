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
@Table(name = "thresholdrule")
public class ThresholdRule extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -367637168110837575L;
	private Double txn_amt;
	private String txn_type;
	private String cust_type;
	private String dr_cr;
	private String frequence;
	private String account_grp;
	private Long baseRuleId;
	public Double getTxn_amt() {
		return txn_amt;
	}
	public void setTxn_amt(Double txn_amt) {
		this.txn_amt = txn_amt;
	}
	public String getTxn_type() {
		return txn_type;
	}
	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	public String getDr_cr() {
		return dr_cr;
	}
	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}
	public String getFrequence() {
		return frequence;
	}
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}
	public String getAccount_grp() {
		return account_grp;
	}
	public void setAccount_grp(String account_grp) {
		this.account_grp = account_grp;
	}
	
	public Long getBaseRuleId() {
		return baseRuleId;
	}
	public void setBaseRuleId(Long baseRuleId) {
		this.baseRuleId = baseRuleId;
	}
	
	@Override
	public String toString() {
		return "ThresholdRule [txn_amt=" + txn_amt + ", txn_type=" + txn_type + ", cust_type=" + cust_type + ", dr_cr="
				+ dr_cr + ", frequence=" + frequence + ", account_grp=" + account_grp + ", baseRuleId=" + baseRuleId
				+ "]";
	}
	
	
	
	

}
