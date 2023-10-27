package br.com.cefet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Veículo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String placa;
	@Enumerated(EnumType.STRING)	
	private Marca marcaVeículo; // enum
	@Column(nullable = false)
	private String modeloVeículo; // pode vir a se tornar enum
	@Enumerated(EnumType.STRING)	
	private Categoria categoriaVeículo; // enum
	private float quilometragem;
	@Enumerated(EnumType.STRING)
	private Paletas cor; // enum
	@Column(nullable = false)
	private byte ano; // byte foi escolhido porque dificilmente carros duram mais de 127 anos
	@Column(nullable = false)
	private String filial; // pode vir a se tornar enum

}
