package br.com.cefet.dto;

import java.util.Date;

import br.com.cefet.model.Estacao;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Usuario;
import br.com.cefet.model.Veiculo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoManutencao {
	@NotNull
	private int idManutencao;
	@Valid // Indica que o objeto veiculo deve ser validado
	private Veiculo veiculo;
	
	private int veiculoId;
	
	@Valid
	private Estacao estacao;
	
	private String estacaoId;
	@NotNull(message = "A data de reserva não pode ser nula.")
    @Future(message = "A data de reserva deve ser no futuro.")
	private Date dataEntrada;
	@NotNull(message = "A data de reserva não pode ser nula.")
	private Date dataSaida;
	private String status;
	private Usuario usuario;

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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		return "requisicaoManutencao [veiculo=" + veiculo + ", veiculoId=" + veiculoId + ", estacao=" + estacao
				+ ", estacaoId=" + estacaoId + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", status="
				+ status + ", usuario=" + usuario + "]";
	}

}
