package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Filial {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int ID;
	private String CNPJ;
	private String endereço;
	private String complemento;
	private short número;
	private String CEP;
	private String bairro;
	private String UF;

}
