package br.com.cefet.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="manutencao")
public class Manutencao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idManutencao;
	@Column(nullable = false, name = "DataEntrada")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataEntrada;
	@Column(nullable = false, name = "DataSaida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataSaida;	
	@ManyToOne
	@JoinColumn(name = "estacaoId")
	private Estacao estacao;
	@ManyToOne
	@JoinColumn(name = "veiculoId")
	private Veiculo veiculo;
	

	public int getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(int idManutencao) {
		this.idManutencao = idManutencao;
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

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Manutencao() {
		super();
	}
	
	public Manutencao(Date dataEntrada, Date dataSaida, Estacao estacao, Veiculo veiculo) {
		super();
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.estacao = estacao;
		this.veiculo = veiculo;
	}

}
	
