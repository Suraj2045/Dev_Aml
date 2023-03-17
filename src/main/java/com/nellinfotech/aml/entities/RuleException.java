package com.nellinfotech.aml.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "RuleException")
public class RuleException extends BaseEntity{

	private static final long serialVersionUID = 5563821685184612813L;
@Column(name = "SENARIO_DESCRIPTION", length = 200, nullable = false)
 private String senarioDescription;
 @Column(name = "CUSTOMER_NAME", length = 200, nullable = false)
 private String customerName;
 @Column(name = "CUST_CODE", length = 200, nullable = false)
 private String cust_code;
 @Column(nullable = true)
// @Temporal(TemporalType.TIMESTAMP)
 private LocalDate fromDate;
 @Column(nullable = true)
// @Temporal(TemporalType.TIMESTAMP)
 private LocalDate toDate;
 @Column(name = "REASON_EXCEPTION", length = 200, nullable = false)
 private String  reasonException;
public String getSenarioDescription() {
	return senarioDescription;
}
public void setSenarioDescription(String senarioDescription) {
	this.senarioDescription = senarioDescription;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getCust_code() {
	return cust_code;
}
public void setCust_code(String cust_code) {
	this.cust_code = cust_code;
}

public LocalDate getFromDate() {
	return fromDate;
}
public void setFromDate(LocalDate fromDate) {
	this.fromDate = fromDate;
}
public LocalDate getToDate() {
	return toDate;
}
public void setToDate(LocalDate toDate) {
	this.toDate = toDate;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public String getReasonException() {
	return reasonException;
}
public void setReasonException(String reasonException) {
	this.reasonException = reasonException;
}
@Override
public String toString() {
	return "RuleException [senarioDescription=" + senarioDescription + ", customerName=" + customerName + ", cust_code="
			+ cust_code + ", fromDate=" + fromDate + ", toDate=" + toDate + ", reasonException=" + reasonException
			+ "]";
}


}
