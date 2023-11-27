package br.com.cefet.dto;

import br.com.cefet.model.Usuario;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Reserva;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class requisicaoCliente {
	@NotNull
	private int id;
	private requisicaoUsuario ru;
	@NotNull
	@NotBlank
	private String endereco;
	@NotNull
	@NotBlank
	private String numero;
	@NotNull
	@NotBlank
	private String bairro;
	@NotNull
	@NotBlank
	private String cidade;
	@NotNull
	@NotBlank
	private String estado;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private List<Reserva> reservas;
	
	
	
	
	
public requisicaoUsuario getRu() {
		return ru;
	}


	public void setRu(requisicaoUsuario ru) {
		this.ru = ru;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	
	


	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		
		cliente.setEndereco(this.endereco);
		cliente.setNumero(this.numero);
		cliente.setBairro(this.bairro);
		cliente.setCidade(this.cidade);
		cliente.setEstado(this.estado);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setReservas(this.reservas);
		
		return cliente;
	} 
	
	
	public Cliente toCliente(Cliente cliente) {
		cliente.setEndereco(this.endereco);
		cliente.setNumero(this.numero);
		cliente.setBairro(this.bairro);
		cliente.setCidade(this.cidade);
		cliente.setEstado(this.estado);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setReservas(this.reservas);
		
		return cliente;
	} 
	
	
	public void fromCliente(Cliente cliente) {
		this.endereco = cliente.getEndereco();
		this.numero = cliente.getNumero();
		this.bairro = cliente.getBairro();
		this.cidade = cliente.getCidade();
		this.estado = cliente.getEstado();
		this.dataNascimento = cliente.getDataNascimento();
		this.reservas = cliente.getReservas();
		}


	@Override
	public String toString() {
		return "requisicaoCliente [ru=" + ru + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + ", dataNascimento=" + dataNascimento + ", reservas="
				+ reservas + "]";
	}




	
	
	
	}

