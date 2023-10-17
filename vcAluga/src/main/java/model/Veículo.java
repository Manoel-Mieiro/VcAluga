package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Veículo {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int ID;
	private String placa;
	private Marca marcaVeículo; // enum
	private String modeloVeículo; // pode vir a se tornar enum
	private Categoria categoriaVeículo; // enum
	private float quilometragem;
	private Paletas cor; // enum
	private byte ano; // byte foi escolhido porque dificilmente carros duram mais de 127 anos
	private String filial; // pode vir a se tornar enum

}
