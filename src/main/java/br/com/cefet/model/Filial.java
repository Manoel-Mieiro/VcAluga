package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Filial {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int idFilial;
	private int nomeFilial;
	private String cnpj;
	private String endereço;
	private String complemento;
	private short número;
	private String cep;
	private String bairro;
	private String uf;
	private int quantidadeVeículos;	//atributo da antiga classe estoque
	private float cota;	//atributo usado para calcular qtd a ser reservada pela filial
}
