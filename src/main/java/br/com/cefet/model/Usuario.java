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
	private String nome;
	private String sobrenome; // nome paterno
	private String email;
	private Conta tipo;	//enum para selecionar tipo de conta e pedir a devida credencial
	private String senha;
	private String telefone;
	private String cpf; 
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Conta getTipo() {
		return tipo;
	}

	public void setTipo(Conta tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
