package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Usuario {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	private String nomeCompleto;
	private Conta tipo;	//enum para selecionar tipo de conta e pedir a devida credencial
	private String email;
	private String telefone;
	private String senha;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}



	public Usuario(String nomeCompleto, String email, String senha) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
	}



	public Usuario(int id, String nomeCompleto, Conta tipo, String email, String telefone, String senha) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.tipo = tipo;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNomeCompleto() {
		return nomeCompleto;
	}



	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}



	public Conta getTipo() {
		return tipo;
	}



	public void setTipo(Conta tipo) {
		this.tipo = tipo;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
