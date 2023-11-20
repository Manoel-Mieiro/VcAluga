package br.com.cefet.model;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Filial {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private String complemento;
	@Column(nullable = false)
	private short numero;
	@Column(nullable = false)
	private String cep;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String uf;
	@Column(nullable = false)
	private int quantidadeVeiculos;	//atributo da antiga classe estoque
	@Column(nullable = false)
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
	
	
	public Filial() {
		super();
	}
	public Filial(String cnpj, String endereco, String complemento, short numero, String cep, String bairro, String uf,
			int quantidadeVeiculos, float cota) {
		super();
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.complemento = complemento;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.uf = uf;
		this.quantidadeVeiculos = quantidadeVeiculos;
		this.cota = cota;
	}
	@Override
	public String toString() {
		return "Filial [id=" + id + ", cnpj=" + cnpj + ", endereco=" + endereco + ", complemento=" + complemento
				+ ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro + ", uf=" + uf + ", quantidadeVeiculos="
				+ quantidadeVeiculos + ", cota=" + cota + "]";
	}
	
	
}
