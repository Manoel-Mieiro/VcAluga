package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Relatorio {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	private int veiculosManutencaoo;
	private int veiculosReservados;
	private int veiculosCota;
	private int veiculosDisponiveis; //pode ser retorno de função

}
