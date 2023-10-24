package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Filial {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	private String cnpj;
	private String endereço;
	private String complemento;
	private short número;
	private String cep;
	private String bairro;
	private String uf;

}
