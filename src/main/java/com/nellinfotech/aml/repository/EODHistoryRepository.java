package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.EODHistory;


public interface EODHistoryRepository extends JpaRepository<EODHistory, Long> {

}
