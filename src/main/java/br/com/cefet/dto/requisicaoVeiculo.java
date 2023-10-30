package br.com.cefet.dto;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoVeiculo {
	@NotBlank
	@NotNull
	private String placa;
	private Marca marcaVeiculo;
	@NotBlank
	@NotNull
	private String modeloVeiculo;
	private Categoria categoriaVeiculo;
	@NotNull
	@Min(value=0)
	@Max(value=100000)
	private float quilometragem;
	private Paletas cor;
	@NotNull
	@Min(value=2013)
	@Max(value=2024)
	@Digits(integer=6, fraction=2)
	private int ano;
	@NotBlank
	@NotNull
	private String filial;
	
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
	
public Veiculo toVeiculo() {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca(this.placa);
		veiculo.setMarcaVeiculo(this.marcaVeiculo);
		veiculo.setModeloVeiculo(this.modeloVeiculo);
		veiculo.setCategoriaVeiculo(this.categoriaVeiculo);
		veiculo.setQuilometragem(this.quilometragem);
		veiculo.setCor(this.cor);
		veiculo.setAno(this.ano);
		veiculo.setFilial(this.filial);
		
		
		return veiculo;
	} 
	
	@Override
	public String toString() {
		return "requisicaoVeiculo [placa=" + placa + ", marcaVeiculo=" + marcaVeiculo + ", modeloVeiculo="
				+ modeloVeiculo + ", categoriaVeiculo=" + categoriaVeiculo + ", quilometragem=" + quilometragem
				+ ", cor=" + cor + ", ano=" + ano + ", filial=" + filial + "]";
	}
	
	
	
	}
