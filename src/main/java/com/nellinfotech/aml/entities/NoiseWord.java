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
@Table(name = "NOISE_WORDS")
public class NoiseWord extends BaseEntity {

	private String noiseword;

	public String getNoiseword() {
		return noiseword;
	}

	public void setNoiseword(String noiseword) {
		this.noiseword = noiseword;
	}

	@Override
	public String toString() {
		return "NoiseWord [noiseword=" + noiseword + "]";
	}
	
	
}
