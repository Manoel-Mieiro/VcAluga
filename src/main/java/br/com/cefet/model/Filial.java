package br.com.cefet.model;

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
	private String endereco;
	private String complemento;
	private short numero;
	private String cep;
	private String bairro;
	private String uf;
	private int quantidadeVeiculos;	//atributo da antiga classe estoque
	private float cota;	//atributo usado para calcular qtd a ser reservada pela filial
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public short getNumero() {
		return numero;
	}
	public void setNumero(short numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public int getQuantidadeVeiculos() {
		return quantidadeVeiculos;
	}
	public void setQuantidadeVeiculos(int quantidadeVeiculos) {
		this.quantidadeVeiculos = quantidadeVeiculos;
	}
	public float getCota() {
		return cota;
	}
	public void setCota(float cota) {
		this.cota = cota;
	}
	
	
}
