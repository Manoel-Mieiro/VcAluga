package br.com.cefet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Funcionario extends Usuario{
	@Column(nullable = false)
	private String matricula;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Cargo cargo;	//enum
	public String getmatricula() {
		return matricula;
	}
	public void setmatricula(String matricula) {
		this.matricula = matricula;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	public Funcionario() {
		super();
	}
	public Funcionario(String matricula, Cargo cargo) {
		super();
		this.matricula = matricula;
		this.cargo = cargo;
	}
	@Override
	public String toString() {
		return "Funcionário [matricula=" + matricula + ", cargo=" + cargo + "]";
	}
	
	
	
	
	
}
