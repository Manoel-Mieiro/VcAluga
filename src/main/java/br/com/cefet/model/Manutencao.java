package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Manutencao {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	/* usar Date para */
	private String dataEntrada;
	private String dataSaida;	
	private Estacao estacao;	//objeto do tipo estação
}
