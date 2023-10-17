package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Relatório {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int ID;
	private int veículosManutenção;
	private int veículosReservados;
	private int veículosCota;
	private int veículosDisponíveis;

}
