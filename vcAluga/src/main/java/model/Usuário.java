package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Usuário {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	private String nome;
	private String sobrenome; // nome paterno
	
	public Usuário() {
		// TODO Auto-generated constructor stub
	}

	public Usuário(String nome, String sobrenome) {
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
	
	
	
	
}
