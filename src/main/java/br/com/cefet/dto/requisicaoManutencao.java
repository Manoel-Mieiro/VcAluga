package br.com.cefet.dto;

import java.util.Date;

import br.com.cefet.model.Estacao;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.NotNull;

public class requisicaoManutencao {
	private Veiculo veiculo;
    @NotNull
    private int veiculoId; 
	@NotNull
	private Estacao estacao;
	@NotNull
	private int estacaoId;
	@NotNull
	private Date dataEntrada;
	@NotNull
	private Date dataSaida;

	
public Manutencao toManutencao() {
		Manutencao manutencao = new Manutencao();
		manutencao.setVeiculo(this.veiculo);
		manutencao.setEstacao(this.estacao);
		manutencao.setDataEntrada(this.dataEntrada);
		manutencao.setDataSaida(this.dataSaida);
		
		return manutencao;
	} 

public Manutencao toManutencao(Manutencao manutencao) {
	manutencao.setVeiculo		(this.veiculo);
	manutencao.setEstacao		(this.estacao);
	manutencao.setDataEntrada	(this.dataEntrada);
	manutencao.setDataSaida		(this.dataSaida);
	
	return manutencao;
} 

public void fromManutencao(Manutencao manutencao) {
	this.veiculo  = manutencao.getVeiculo();
	this.estacao = manutencao.getEstacao();
	this.dataEntrada = manutencao.getDataEntrada();
	this.dataSaida= manutencao.getDataSaida();
}

@Override
public String toString() {
	return "requisicaoManutencao [veiculo=" + veiculo + ", estacao=" + estacao + ", dataEntrada=" + dataEntrada
			+ ", dataSaida=" + dataSaida + "]";
}


	
	
	
	
	
	}
