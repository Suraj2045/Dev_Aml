package com.nellinfotech.aml.entities;

import java.util.Date;

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
@Table(name = "EOD_History")
public class EODHistory extends BaseEntity {

	@Column(name = "PROCESS_NAME", length = 50, nullable = false)
    private String processName;
    
  
   @Column(name = "PROCESS_ID", length = 50, nullable = false)
    private String processId;
   
   @Column(name = "START_TIME", length = 150, nullable = true)
   private Date startTime;
   
   @Column(name = "END_TIME", length = 150, nullable = true)
   private Date endTime;

		public String getProcessName() {
			return processName;
		}
		
		public void setProcessName(String processName) {
			this.processName = processName;
		}
		
		public String getProcessId() {
			return processId;
		}
		
		public void setProcessId(String processId) {
			this.processId = processId;
		}
		
		public Date getStartTime() {
			return startTime;
		}
		
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		
		public Date getEndTime() {
			return endTime;
		}
		
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
   
   
}
