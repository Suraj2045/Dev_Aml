package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nellinfotech.aml.entities.EODMst;


public interface EODRepository extends JpaRepository<EODMst, Long> {


	public EODMst findByProcessId(String processId);

	@Modifying
	@Query (value = "update eod_master set frequency =:frequency, next_run_date =:nextRunDate, upload =:upload "
	+ "where id =:id", nativeQuery=true)
	public void saveEodList(long id, String frequency, String nextRunDate, String upload);

}
