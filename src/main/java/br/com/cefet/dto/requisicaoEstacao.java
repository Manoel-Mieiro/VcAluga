package br.com.cefet.dto;

import br.com.cefet.model.Estacao;
import br.com.cefet.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoEstacao {
	@NotBlank
	@NotNull
	private String cep;
	@NotBlank
	@NotNull
	private String endereco;
	@NotNull
	private short numero;
	private Status status;
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public short getNumero() {
		return numero;
	}
	public void setNumero(short numero) {
		this.numero = numero;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
public Estacao toEstacao() {
		Estacao estacao = new Estacao();
		estacao.setCep(this.cep);
		estacao.setEndereco(this.endereco);
		estacao.setNumero(this.numero);
		estacao.setStatus(this.status);
		
		
		return estacao;
	} 

public Estacao toEstacao(Estacao estacao) {
	estacao.setCep(this.cep);
	estacao.setEndereco(this.endereco);
	estacao.setNumero(this.numero);
	estacao.setStatus(this.status);
	
	
	return estacao;
} 

public void fromEstacao(Estacao estacao) {
	this.cep = estacao.getCep();
	this.endereco = estacao.getEndereco();
	this.numero = estacao.getNumero();
	this.status = estacao.getStatus();
	} 
	
	@Override
	public String toString() {
		return "requisicaoEstacao [cep=" + cep + ", endereco=" + endereco + ", numero="
				+ numero + ", status=" + status + "]";
	} 
	
	}
