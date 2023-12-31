package br.com.cefet.dto;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoVeiculo {
	@NotNull
	private int id;
	@NotBlank
	@NotNull
	private String placa;
	private Marca marcaVeiculo;
	@NotBlank
	@NotNull
	private String modeloVeiculo;
	private Categoria categoriaVeiculo;
	@NotNull
	@Min(value = 0)
	@Max(value = 100000)
	private float quilometragem;
	private Paletas cor;
	@NotNull
	@Min(value = 2013)
	@Max(value = 2024)
	@Digits(integer = 6, fraction = 2)
	private int ano;
//	private String filial;
	@NotNull
	private Filial branch;
	@NotNull
	private int branchId;
	@NotNull
	private String status;
	private boolean emManutencao;
	
//	@NotNull
//	private String uf;

	
	
	public int getBranchId() {
		return branchId;
	}

	public boolean isEmManutencao() {
		return emManutencao;
	}

	public void setEmManutencao(boolean emManutencao) {
		this.emManutencao = emManutencao;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
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

//	public String getFilial() {
//		return filial;
//	}
//
//	public void setFilial(String filial) {
//		this.filial = filial;
//	}
	
	

	public int getId() {
		return id;
	}

	public @NotNull String getStatus() {
		return status;
	}

	public void setStatus(@NotNull String status) {
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Filial getBranch() {
		return branch;
	}

	public void setBranch(Filial branch) {
		this.branch = branch;
	}

//	public String getUf() {
//		return uf;
//	}
//
//	public void setUf(String uf) {
//		this.uf = uf;
//	}

	public Veiculo toVeiculo() {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca(this.placa);
		veiculo.setMarcaVeiculo(this.marcaVeiculo);
		veiculo.setModeloVeiculo(this.modeloVeiculo);
		veiculo.setCategoriaVeiculo(this.categoriaVeiculo);
		veiculo.setQuilometragem(this.quilometragem);
		veiculo.setCor(this.cor);
		veiculo.setAno(this.ano);
		veiculo.setStatus(this.status);
		veiculo.setEmManutencao(this.emManutencao);
//		veiculo.setFilial(this.filial);
//		veiculo.setBranch(this.branch);
		
		
		return veiculo;
	}

//Metodo toVeiculo para update de um veiculo existente
	public Veiculo toVeiculo(Veiculo veiculo) {
		veiculo.setPlaca(this.placa);
		veiculo.setMarcaVeiculo(this.marcaVeiculo);
		veiculo.setModeloVeiculo(this.modeloVeiculo);
		veiculo.setCategoriaVeiculo(this.categoriaVeiculo);
		veiculo.setQuilometragem(this.quilometragem);
		veiculo.setCor(this.cor);
		veiculo.setAno(this.ano);
		veiculo.setStatus(this.status);
		veiculo.setEmManutencao(this.emManutencao);
//		veiculo.setFilial(this.filial);
//		veiculo.setBranch(this.branch);

		return veiculo;
	}

	public void fromVeiculo(Veiculo veiculo) {
		this.placa = veiculo.getPlaca();
		this.marcaVeiculo = veiculo.getMarcaVeiculo();
		this.modeloVeiculo = veiculo.getModeloVeiculo();
		this.categoriaVeiculo = veiculo.getCategoriaVeiculo();
		this.quilometragem = veiculo.getQuilometragem();
		this.cor = veiculo.getCor();
		this.ano = veiculo.getAno();
		this.status = veiculo.getStatus();
		this.emManutencao = veiculo.isEmManutencao();
//		this.filial = veiculo.getFilial();
//		this.branch = veiculo.getBranch();
	}

	@Override
	public String toString() {
		return "requisicaoVeiculo [placa=" + placa + ", marcaVeiculo=" + marcaVeiculo + ", modeloVeiculo="
				+ modeloVeiculo + ", categoriaVeiculo=" + categoriaVeiculo + ", quilometragem=" + quilometragem
				+ ", cor=" + cor + ", ano=" + ano + ", branch=" + branch + ", branchId=" + branchId + ", status="
				+ status + ", emManutencao=" + emManutencao + "]";
	}



}
