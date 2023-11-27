package br.com.cefet.dto;

import br.com.cefet.model.Filial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class requisicaoFilial {
	@NotNull
	private int idFilial;
	@NotBlank
	@NotNull
	private String cnpj;
	@NotBlank
	@NotNull
	private String endereco;
	@NotBlank
	@NotNull
	private String complemento;
	@NotNull
	private short numero;
	@NotBlank
	@NotNull
	@Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido")
	private String cep;
	@NotBlank
	@NotNull
	private String bairro;
	@NotBlank
	@NotNull
	private String uf;
	@NotNull
	private int quantidadeVeiculos;
	@NotNull
	private float cota;
	
	
public int getIdFilial() {
		return idFilial;
	}

//	public void setIdFilial(int idFilial) {
//		this.idFilial = idFilial;
//	}

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

public Filial toFilial() {
		Filial filial = new Filial();
//		filial.setId(this.idFilial);
		filial.setCnpj(this.cnpj);
		filial.setEndereco(this.endereco);
		filial.setComplemento(this.complemento);
		filial.setNumero(this.numero);
		filial.setCep(this.cep);
		filial.setBairro(this.bairro);
		filial.setUf(this.uf);
		filial.setQuantidadeVeiculos(this.quantidadeVeiculos);
		filial.setCota(this.cota);
		
		
		return filial;
	} 

//Metodo toVeiculo para update de um veiculo existente
public Filial toFilial(Filial filial) {
//	filial.setId(this.idFilial);
	filial.setCnpj(this.cnpj);
	filial.setEndereco(this.endereco);
	filial.setComplemento(this.complemento);
	filial.setNumero(this.numero);
	filial.setCep(this.cep);
	filial.setBairro(this.bairro);
	filial.setUf(this.uf);
	filial.setQuantidadeVeiculos(this.quantidadeVeiculos);
	filial.setCota(this.cota);
	
	
	return filial;
} 

public void fromFilial(Filial filial) {
//	this.idFilial = filial.getIdFilial();
	this.cnpj = filial.getCnpj();
	this.endereco = filial.getEndereco();
	this.complemento = filial.getComplemento();
	this.numero = filial.getNumero();
	this.cep = filial.getCep();
	this.bairro = filial.getBairro();
	this.uf = filial.getUf();
	this.quantidadeVeiculos = filial.getQuantidadeVeiculos();
	this.cota = filial.getCota();
	
}

@Override
public String toString() {
	return "requisicaoFilial [cnpj=" + cnpj + ", endereco=" + endereco + ", complemento=" + complemento + ", numero="
			+ numero + ", cep=" + cep + ", bairro=" + bairro + ", uf=" + uf + ", quantidadeVeiculos="
			+ quantidadeVeiculos + ", cota=" + cota + "]";
} 
	

	
	
	}
