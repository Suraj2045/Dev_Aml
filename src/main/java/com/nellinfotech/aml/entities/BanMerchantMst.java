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
@Table(name = "BAN_MERCHANT_MASTER")
public class BanMerchantMst extends BaseEntity{

	 private String merchantscode;
	 private String merhantname;
	 private String status;
	 private String banreason;
	 
	public String getMerchantscode() {
		return merchantscode;
	}
	public void setMerchantscode(String merchantscode) {
		this.merchantscode = merchantscode;
	}
	public String getMerhantname() {
		return merhantname;
	}
	public void setMerhantname(String merhantname) {
		this.merhantname = merhantname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBanreason() {
		return banreason;
	}
	public void setBanreason(String banreason) {
		this.banreason = banreason;
	}
	@Override
	public String toString() {
		return "BanMerchantMst [merchantscode=" + merchantscode + ", merhantname=" + merhantname + ", status=" + status
				+ ", banreason=" + banreason + "]";
	}
	 
	 
}
