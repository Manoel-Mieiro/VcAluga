package br.com.cefet.model;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Cliente extends Usuario{
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = false, name = "DataNascimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;
	@OneToMany(mappedBy = "cliente")  
    private List<Motorista> motoristas;
	@OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos;

	
	
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public List<Motorista> getMotoristas() {
		return motoristas;
	}
	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public Cliente() {
		super();
	}
	public Cliente(String endereco, String numero, String bairro, String cidade, String estado, Date dataNascimento,
			List<Reserva> reservas, List<Motorista> motoristas, List<Contrato> contratos) {
		super();
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.dataNascimento = dataNascimento;
		this.reservas = reservas;
		this.motoristas = motoristas;
		this.contratos = contratos;
	}

	
	

	
	
}
