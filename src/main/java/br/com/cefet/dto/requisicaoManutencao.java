package br.com.cefet.dto;

import java.util.Date;

import br.com.cefet.model.Estacao;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.NotNull;

public class requisicaoManutencao {
	@NotNull
	private int idManutencao;
	@NotNull
	private Veiculo veiculo;
	@NotNull
	private int veiculoId;
	@NotNull
	private Estacao estacao;
	@NotNull
	private String estacaoId;
	@NotNull
	private Date dataEntrada;
	@NotNull
	private Date dataSaida;
	

	public int getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(int idManutencao) {
		this.idManutencao = idManutencao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public int getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(int veiculoId) {
		this.veiculoId = veiculoId;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public String getEstacaoId() {
		return estacaoId;
	}

	public void setEstacaoId(@NotNull String estacaoId) {
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
//		manutencao.setVeiculo(veiculo);
//		manutencao.setEstacao(estacao);
		manutencao.setDataEntrada(this.dataEntrada);
		manutencao.setDataSaida(this.dataSaida);

		return manutencao;
	}

	public Manutencao toManutencao(Manutencao manutencao) {
//		manutencao.setVeiculo(veiculo);
//		manutencao.setEstacao(estacao);
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
