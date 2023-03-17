package com.nellinfotech.aml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nellinfotech.aml.entities.NoiseWord;

public interface NoiseWordRepository extends JpaRepository<NoiseWord, Long>{

	List<NoiseWord> getNoiseWordByBankCode(String bankCode);

}
