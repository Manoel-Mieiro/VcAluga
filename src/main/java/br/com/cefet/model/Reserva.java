package br.com.cefet.model;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;

	@ManyToOne

	@JoinColumn(name = "idVeiculo")
	private Veiculo veiculo;

	// @ManyToMany // private Cliente cliente;

	@Column(nullable = false, name = "DataReserva")
	private LocalDate dataReserva;

	@Column(nullable = false, name = "DataDevolucao")
	private LocalDate dataDevolucao;

	@Column(nullable = false, name = "ValorPago")
	private float valorPago;

	public Reserva() {
		super();
	}



	public int getIdReserva() {
		return idReserva;
	}



	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}



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



	public Reserva(Veiculo veiculo, LocalDate dataReserva, LocalDate dataDevolucao, float valorPago) {
		super();
		this.veiculo = veiculo;
		this.dataReserva = dataReserva;
		this.dataDevolucao = dataDevolucao;
		this.valorPago = valorPago;
	}



}
