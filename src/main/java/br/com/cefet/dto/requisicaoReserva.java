package br.com.cefet.dto;

import java.time.LocalDate;
import java.util.Date;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoReserva {
	@NotNull
	private Veiculo veiculo;
    @NotNull
    private int veiculoId; // Campo adicional para armazenar o ID do veículo
	@NotNull
	//adicionar notação para D+1
	private LocalDate dataReserva;
	@NotNull
	//limitar x dias
	private LocalDate dataDevolucao;
	@NotNull
	private float valorPago;

public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDate dataReserva) {
		this.dataReserva = dataReserva;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public float getValorPago() {
		return valorPago;
	}

	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}

public Reserva toReserva() {
		Reserva reserva = new Reserva();
		reserva.setVeiculo(this.veiculo);
		reserva.setDataReserva(this.dataReserva);
		reserva.setDataDevolucao(this.dataDevolucao);
		reserva.setValorPago(this.valorPago);
				
		return reserva;
	} 

//Metodo toVeiculo para update de um veiculo existente
public Reserva toReserva(Reserva reserva) {
	reserva.setVeiculo(this.veiculo);
	reserva.setDataReserva(this.dataReserva);
	reserva.setDataDevolucao(this.dataDevolucao);
	reserva.setValorPago(this.valorPago);
	
	return reserva;
} 

public void fromReserva(Reserva reserva) {
	this.veiculo = reserva.getVeiculo();
	this.dataReserva = reserva.getDataReserva();
	this.dataDevolucao = reserva.getDataDevolucao();
	this.valorPago = reserva.getValorPago();
}

@Override
public String toString() {
	return "requisicaoReserva [veiculo=" + veiculo + ", dataReserva=" + dataReserva + ", dataDevolucao=" + dataDevolucao
			+ ", valorPago=" + valorPago + "]";
} 
	
	
	
	
	
	}
