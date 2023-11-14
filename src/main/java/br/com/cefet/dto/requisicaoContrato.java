package br.com.cefet.dto;

import java.util.ArrayList;
import java.util.Date;

import br.com.cefet.model.Contrato;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoContrato {
	@NotNull
	private int idContrato;
	@NotNull
	private Filial cnpj;
	@NotNull
	private Veiculo placa;
	@NotNull
	private Date dataEmissao;
	@NotBlank
	@NotNull
	private String assinaturaGestor;
	@NotBlank
	@NotNull
	private String assinaturaCliente;
	@NotBlank
	@NotNull
	private ArrayList<String> cnhs;

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public Filial getCnpj() {
		return cnpj;
	}

	public void setCnpj(Filial cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getAssinaturaGestor() {
		return assinaturaGestor;
	}

	public void setAssinaturaGestor(String assinaturaGestor) {
		this.assinaturaGestor = assinaturaGestor;
	}

	public String getAssinaturaCliente() {
		return assinaturaCliente;
	}

	public void setAssinaturaCliente(String assinaturaCliente) {
		this.assinaturaCliente = assinaturaCliente;
	}

	public ArrayList<String> getCnhs() {
		return cnhs;
	}

	public void setCnhs(ArrayList<String> cnhs) {
		this.cnhs = cnhs;
	}

	public Veiculo getPlaca() {
		return placa;
	}

	public void setPlaca(Veiculo placa) {
		this.placa = placa;
	}

	public Contrato toContrato() {
		Contrato contrato = new Contrato();
		Filial filial = new Filial();
		Veiculo veiculo = new Veiculo();
		contrato.setCnpj(filial);
		contrato.setPlaca(veiculo);
		contrato.setDataEmissão(this.dataEmissao);
		contrato.setAssinaturaCliente(this.assinaturaCliente);
		contrato.setAssinaturaGestor(this.assinaturaGestor);
		return contrato;
	}

	public Contrato toContrato(Contrato contrato) {
		Filial filial = new Filial();
		Veiculo veiculo = new Veiculo();
		contrato.setPlaca(veiculo);
		contrato.setCnpj(filial);
		contrato.setDataEmissão(this.dataEmissao);
		contrato.setAssinaturaCliente(this.assinaturaCliente);
		contrato.setAssinaturaGestor(this.assinaturaGestor);
		return contrato;
	}

	public void fromContrato(Contrato contrato) {
		this.placa = contrato.getPlaca();
		this.cnpj = contrato.getCnpj();
		this.dataEmissao = contrato.getDataEmissão();
		this.assinaturaCliente = contrato.getAssinaturaCliente();
		this.assinaturaGestor = contrato.getAssinaturaGestor();
	}

	@Override
	public String toString() {
		return "requisicaoContrato [cnpj=" + cnpj + ", placa=" + placa + ", dataEmissao=" + dataEmissao
				+ ", assinaturaGestor=" + assinaturaGestor + ", assinaturaCliente=" + assinaturaCliente + ", cnhs="
				+ cnhs + "]";
	}

}
