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
@Table(name = "BAN_MATERIAL_MASTER")
public class BanMaterialMst extends BaseEntity{
	 private String materialcode;
	 private String materialname;
	 private String fromdate;
	 private String tilldate;
	public String getMaterialcode() {
		return materialcode;
	}
	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTilldate() {
		return tilldate;
	}
	public void setTilldate(String tilldate) {
		this.tilldate = tilldate;
	}
	
	@Override
	public String toString() {
		return "BanMaterialMst [materialcode=" + materialcode + ", materialname=" + materialname + ", fromdate="
				+ fromdate + ", tilldate=" + tilldate + "]";
	}
	 
	 
}
