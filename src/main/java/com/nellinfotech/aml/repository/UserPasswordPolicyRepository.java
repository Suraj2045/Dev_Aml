package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.UserPasswordPolicyEntity;

public interface UserPasswordPolicyRepository extends JpaRepository<UserPasswordPolicyEntity, Long> {
    
    public List<UserPasswordPolicyEntity> findByBankCode(String bankCode);
    
}
