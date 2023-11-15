package br.com.cefet.dto;

import br.com.cefet.model.Cargo;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoFuncionario {
	@NotBlank
	@NotNull
	private String matricula;
	@NotBlank
	@NotNull
	private Cargo cargo;
	@NotBlank
	@NotNull
	private String filial;
	
	
public Funcionario toFuncionario() {
		Funcionario funcionario = new Funcionario(filial, cargo, filial);
		funcionario.setMatricula(this.matricula);
		funcionario.setCargo(this.cargo);
		funcionario.setFilial(this.filial);
		
		return funcionario;
	} 

public Funcionario toFuncionario(Funcionario funcionario) {
	funcionario.setMatricula(this.matricula);
	funcionario.setCargo(this.cargo);
	funcionario.setFilial(this.filial);
	
	return funcionario;
} 

public void fromFuncionario(Funcionario funcionario) {
	this.matricula = funcionario.getMatricula();
	this.cargo = funcionario.getCargo();
	this.filial = funcionario.getFilial();
	}

@Override
public String toString() {
	return "requisicaoFuncionario [matricula=" + matricula + ", cargo=" + cargo + ", filial=" + filial 
			+ "]";
}

	}
