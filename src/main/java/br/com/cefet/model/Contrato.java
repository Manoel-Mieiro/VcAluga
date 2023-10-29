package br.com.cefet.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Contrato {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int idContrato;
//	private String cnpj; FK
	private String dataEmissao;
//	private String cpfCliente; FK
//	private String nomeCompleto; FK
	private String assinaturaGestor;
	private String assinaturaCliente;
	private ArrayList<String> cnhs = new ArrayList<String>(); // coleção de CNHs
}
