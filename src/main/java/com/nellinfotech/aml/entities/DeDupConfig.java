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
@Table(name = "DE_DUP_CONFIG")
public class DeDupConfig extends BaseEntity{
	private String custEntity;
	private String custField;
	public String getCustEntity() {
		return custEntity;
	}
	public void setCustEntity(String custEntity) {
		this.custEntity = custEntity;
	}
	public String getCustField() {
		return custField;
	}
	public void setCustField(String custField) {
		this.custField = custField;
	}
	@Override
	public String toString() {
		return "DeDupConfig [custEntity=" + custEntity + ", custField=" + custField + "]";
	}
	
		
}
