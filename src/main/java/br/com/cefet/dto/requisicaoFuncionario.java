package br.com.cefet.dto;

import br.com.cefet.model.Usuario;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cefet.model.Cargo;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Reserva;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class requisicaoFuncionario {
	@NotNull
	private int id;
	private requisicaoUsuario ru;
	@NotNull
	@NotBlank
//	Colocar máscara com parttern
	private String matricula;
	private Cargo cargo;
	
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public requisicaoUsuario getRu() {
		return ru;
	}


	public void setRu(requisicaoUsuario ru) {
		this.ru = ru;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Cargo getCargo() {
		return cargo;
	}


	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	public Funcionario toFuncionario() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setmatricula(this.matricula);
		funcionario.setCargo(this.cargo);
		
		
		return funcionario;
	} 
	
	
	public Funcionario toFuncionario(Funcionario funcionario) {
		funcionario.setmatricula(this.matricula);
		funcionario.setCargo(this.cargo);
		
		
		return funcionario;
	} 
	
	
	public void fromFuncionario(Funcionario funcionario) {
		this.matricula = funcionario.getmatricula();
		this.cargo = funcionario.getCargo();
		}


	@Override
	public String toString() {
		return "requisicaoFuncionario [ru=" + ru + ", matricula=" + matricula + ", cargo=" + cargo + "]";
	}



	}

