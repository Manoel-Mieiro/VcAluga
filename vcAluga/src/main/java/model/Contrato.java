package model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Contrato {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int ID;
	private String CNPJ;
	private String dataEmissão;
	private String CPF_Cliente;
	private String nomeCompleto;
	private String assinaturaGestor;
	private String assinaturaCliente;
	private ArrayList<String> CNHs = new ArrayList<String>(); // coleção de CNHs
}
