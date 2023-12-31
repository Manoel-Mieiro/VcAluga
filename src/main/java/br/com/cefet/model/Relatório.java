package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Relatório {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	private int veículosManutenção;
	private int veículosReservados;
	private int veículosCota;
	private int veículosDisponíveis; //pode ser retorno de função

}
