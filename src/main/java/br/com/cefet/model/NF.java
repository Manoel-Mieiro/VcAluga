package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class NF {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNF;
	@Column(nullable = false)	
	private int numeroNF;
	
	public int getIdNF() {
		return idNF;
	}
	public void setIdNF(int idNF) {
		this.idNF = idNF;
	}
	public int getNumeroNF() {
		return numeroNF;
	}
	public void setNumeroNF(int numeroNF) {
		this.numeroNF = numeroNF;
	}

}
