package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Estacao {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int idEstacao;
	private String endereço;
	private String cep;
	private short numero;
	private Status statusEstacao; // enum	
}
