package br.com.cefet.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "reserva")
public class Reserva {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int idReserva;
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="idVeiculo") private Veiculo veiculo;
	 * 
	 * // @ManyToMany // private Cliente cliente;
	 * 
	 * @Column(nullable = false, name="DataReserva") private String dataReserva;
	 * 
	 * @Column(nullable = false, name="DataDevolucao") private String dataDevolucao;
	 * 
	 * @Column(nullable = false, name="ValorPago") private float valorPago;
	 * 
	 * public Reserva() { super(); }
	 * 
	 * public Reserva(int id, Veiculo veiculo, String dataReserva, String
	 * dataDevolução, float valorPago) { super(); this.idReserva = id; this.veiculo
	 * = veiculo; this.dataReserva = dataReserva; this.dataDevolucao =
	 * dataDevolução; this.valorPago = valorPago; }
	 * 
	 * public int getId() { return idReserva; }
	 * 
	 * public void setId(int id) { this.idReserva = id; }
	 * 
	 * public Veiculo getVeiculo() { return veiculo; }
	 * 
	 * public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
	 * 
	 * public String getDataReserva() { return dataReserva; }
	 * 
	 * public void setDataReserva(String dataReserva) { this.dataReserva =
	 * dataReserva; }
	 * 
	 * public String getDataDevolução() { return dataDevolucao; }
	 * 
	 * public void setDataDevolução(String dataDevolução) { this.dataDevolucao =
	 * dataDevolução; }
	 * 
	 * public float getValorPago() { return valorPago; }
	 * 
	 * public void setValorPago(float valorPago) { this.valorPago = valorPago; }
	 * 
	 */}
