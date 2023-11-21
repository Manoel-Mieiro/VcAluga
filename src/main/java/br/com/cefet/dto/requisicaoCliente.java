package br.com.cefet.dto;

import br.com.cefet.model.Usuario;

import java.sql.Date;
import java.util.List;

import br.com.cefet.model.Conta;
import br.com.cefet.model.Reserva;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoCliente {
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private List<Reserva> reservas;
	
	
	
	




	}
