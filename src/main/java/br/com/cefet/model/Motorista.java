package br.com.cefet.model;



import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Motorista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMotorista;
	@Column(nullable = false)
	private String cnh;
	@ManyToMany
	Set<Veiculo> veiculo;
	
	public int getIdMotorista() {
		return idMotorista;
	}
	public void setIdMotorista(int idMotorista) {
		this.idMotorista = idMotorista;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public Motorista() {
		super();
	}
	public Motorista(String cnh) {
		super();
		this.cnh = cnh;
	}
	
	
	
	
}
