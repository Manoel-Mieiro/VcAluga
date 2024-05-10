package br.com.cefet.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cefet.model.Contrato;
import br.com.cefet.model.NF;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoNF {
	@NotNull
	private int idNF;
	@NotNull
	private int numeroNF;
	@NotNull
	private float valorTotal;
	@Column(nullable = false, name = "data_emissao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
//	@NotNull
//	private Date dataVencimento;
	@NotNull
	private float valorSemImposto;
	@NotNull
	private float valorDoImposto;
//	private float imposto;
	private Contrato contrato;
	private int idContrato;
	
	
	public int getIdNF() {
		return idNF;
	}
	
	public int getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(int numeroNF) {
		this.numeroNF = numeroNF;
	}

public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

//	public Date getDataVencimento() {
//		return dataVencimento;
//	}
//
//	public void setDataVencimento(Date dataVencimento) {
//		this.dataVencimento = dataVencimento;
//	}
	
	

//	public float getImposto() {
//		return imposto;
//	}
//
//	public void setImposto(float imposto) {
//		this.imposto = imposto;
//	}

	public float getValorSemImposto() {
		return valorSemImposto;
	}

	public void setValorSemImposto(float valorSemImposto) {
		this.valorSemImposto = valorSemImposto;
	}

	public float getValorDoImposto() {
		return valorDoImposto;
	}

	public void setValorDoImposto(float valorDoImposto) {
		this.valorDoImposto = valorDoImposto;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public void setIdNF(int idNF) {
		this.idNF = idNF;
	}

public NF toNF() {
		NF nf = new NF();
		nf.setNumeroNF(this.numeroNF);
		nf.setDataEmissao(this.dataEmissao);
		
		return nf;
	} 

public NF toNF(NF nf) {
	nf.setNumeroNF(this.numeroNF);
	nf.setDataEmissao(this.dataEmissao);
	
	return nf;
} 

public void fromNF(NF nf) {
	this.numeroNF = nf.getNumeroNF();
	this.dataEmissao = nf.getDataEmissao();
	
}

@Override
public String toString() {
	return "requisicaoNF [numeroNF=" + numeroNF + ", valorTotal=" + valorTotal + ", dataEmissao=" + dataEmissao
			+ ", valorSemImposto=" + valorSemImposto + ", valorDoImposto=" + valorDoImposto + ", contrato=" + contrato
			+ ", idContrato=" + idContrato + "]";
}


	
	}
