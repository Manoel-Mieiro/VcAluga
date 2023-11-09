package br.com.cefet.dto;

import java.util.Date;

import br.com.cefet.model.Estacao;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoManutencao {
	private Veiculo veiculo;
	@NotBlank
	private String placa;
	@NotNull
	private Estacao estacao;
	@NotNull
	private int estacaoId;
	@NotNull
	private Date dataEntrada;
	@NotNull
	private Date dataSaida;
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public int getEstacaoId() {
		return estacaoId;
	}

	public void setEstacaoId(int estacaoId) {
		this.estacaoId = estacaoId;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Manutencao toManutencao() {
		Manutencao manutencao = new Manutencao();
		manutencao.setVeiculo(this.veiculo);
		manutencao.setEstacao(this.estacao);
		manutencao.setDataEntrada(this.dataEntrada);
		manutencao.setDataSaida(this.dataSaida);

		return manutencao;
	}

	public Manutencao toManutencao(Manutencao manutencao) {
		manutencao.setVeiculo(this.veiculo);
		manutencao.setEstacao(this.estacao);
		manutencao.setDataEntrada(this.dataEntrada);
		manutencao.setDataSaida(this.dataSaida);

		return manutencao;
	}

	public void fromManutencao(Manutencao manutencao) {
		this.veiculo = manutencao.getVeiculo();
		this.estacao = manutencao.getEstacao();
		this.dataEntrada = manutencao.getDataEntrada();
		this.dataSaida = manutencao.getDataSaida();
	}

	@Override
	public String toString() {
		return "requisicaoManutencao [veiculo=" + veiculo + ", estacao=" + estacao + ", dataEntrada=" + dataEntrada
				+ ", dataSaida=" + dataSaida + "]";
	}

}
