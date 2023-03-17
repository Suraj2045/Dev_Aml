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
@Table(name = "AlertSubType_Mst")
public class AlertSubTypeMst extends BaseEntity {

	 private static final long serialVersionUID = -2588441332070288443L;
	  
		@Column(name = "ALERT_CODE", length = 20, nullable = false)
		private String alertCode="";
		
		@Column(name = "ALERT_NAME", length = 20)
		private String alertName="";
		
		@Column(name = "BRANCH_CODE", length = 20, nullable = false)
		 private String branchCode="";
		
		@Column(name = "ALERTSUBTYPE_CODE")
		 private String alertSubTypeCode="";

		public String getAlertCode() {
			return alertCode;
		}

		public void setAlertCode(String alertCode) {
			this.alertCode = alertCode;
		}

		public String getAlertName() {
			return alertName;
		}

		public void setAlertName(String alertName) {
			this.alertName = alertName;
		}

		public String getBranchCode() {
			return branchCode;
		}

		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}

		public String getAlertSubTypeCode() {
			return alertSubTypeCode;
		}

		public void setAlertSubTypeCode(String alertSubTypeCode) {
			this.alertSubTypeCode = alertSubTypeCode;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
}
