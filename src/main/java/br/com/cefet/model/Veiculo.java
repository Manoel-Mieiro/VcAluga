package br.com.cefet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String placa;
	@Enumerated(EnumType.STRING)	
	private Marca marcaVeiculo; // enum
	@Column(nullable = false)
	private String modeloVeiculo; // pode vir a se tornar enum
	@Enumerated(EnumType.STRING)	
	private Categoria categoriaVeiculo; // enum
	private float quilometragem;
	@Enumerated(EnumType.STRING)
	private Paletas cor; // enum
	@Column(nullable = false)
	private int ano; // byte foi escolhido porque dificilmente carros duram mais de 127 anos
	@Column(nullable = false)
	private String filial; // pode vir a se tornar enum
//	private float valorDiaria;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Marca getMarcaVeiculo() {
		return marcaVeiculo;
	}
	public void setMarcaVeiculo(Marca marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}
	public String getModeloVeiculo() {
		return modeloVeiculo;
	}
	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}
	public Categoria getCategoriaVeiculo() {
		return categoriaVeiculo;
	}
	public void setCategoriaVeiculo(Categoria categoriaVeiculo) {
		this.categoriaVeiculo = categoriaVeiculo;
	}
	public float getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(float quilometragem) {
		this.quilometragem = quilometragem;
	}
	public Paletas getCor() {
		return cor;
	}
	public void setCor(Paletas cor) {
		this.cor = cor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getFilial() {
		return filial;
	}
	public void setFilial(String filial) {
		this.filial = filial;
	}
	public Veiculo() {
		
	}
	public Veiculo(String placa, Marca marcaVeiculo, String modeloVeiculo, Categoria categoriaVeiculo,
			float quilometragem, Paletas cor, int ano, String filial) {
		super();
		this.placa = placa;
		this.marcaVeiculo = marcaVeiculo;
		this.modeloVeiculo = modeloVeiculo;
		this.categoriaVeiculo = categoriaVeiculo;
		this.quilometragem = quilometragem;
		this.cor = cor;
		this.ano = ano;
		this.filial = filial;
	}
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", marcaVeiculo=" + marcaVeiculo + ", modeloVeiculo="
				+ modeloVeiculo + ", categoriaVeiculo=" + categoriaVeiculo + ", quilometragem=" + quilometragem
				+ ", cor=" + cor + ", ano=" + ano + ", filial=" + filial + "]";
	}
	
	


}
