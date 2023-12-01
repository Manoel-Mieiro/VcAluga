package br.com.cefet.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Motorista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMotorista;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String sobrenome;
	@Column(nullable = false)
	private String cnh;
	 @ManyToOne
	 @JoinColumn(name = "id_cliente")
	 private Cliente cliente;

	
	 
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

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

	public Motorista(String nome, String sobrenome, String cnh) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cnh = cnh;
	}


	

}
