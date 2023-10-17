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
	private int id;
	private String cnpj;
	private String dataEmissão;
	private String cpfCliente;
	private String nomeCompleto;
	private String assinaturaGestor;
	private String assinaturaCliente;
	private ArrayList<String> cnhs = new ArrayList<String>(); // coleção de CNHs
}
