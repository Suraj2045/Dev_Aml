package com.nellinfotech.aml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.FileUploadLog;

public interface FileUploadLogRepository extends JpaRepository<FileUploadLog, Long> {
    
}
