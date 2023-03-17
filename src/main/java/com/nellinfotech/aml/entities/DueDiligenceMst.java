package com.nellinfotech.aml.entities;

import java.sql.Date;

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
@Table(name = "duediligence_Mst")
public class DueDiligenceMst extends BaseEntity{
	
	
	private static final long serialVersionUID = 2220046628953539994L;
	
	
	@Column(name = "BRANCH_CODE", length = 20, nullable = false)
	private String branchCode="";
	
	@Column(name = "ENTITY_CODE", length = 20, nullable = false)
	private String entityCode="";
	
	@Column(name = "FIELD_NAME", length = 200, nullable = false)
	private String fieldname="";
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	
}
