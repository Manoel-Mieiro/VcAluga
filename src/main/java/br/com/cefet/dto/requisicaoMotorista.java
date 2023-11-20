package br.com.cefet.dto;

import br.com.cefet.model.Motorista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoMotorista {
	@NotNull
	private int id;
	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	private String sobrenome;
	@NotBlank
	@NotNull
	private String cnh;

public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

public Motorista toMotorista() {
		Motorista motorista = new Motorista();
		motorista.setId(this.id);
		motorista.setCnh(this.cnh);
		motorista.setNome(this.nome);
		motorista.setSobrenome(this.sobrenome);
		
		return motorista;
	} 

//Metodo toVeiculo para update de um veiculo existente
public Motorista toMotorista(Motorista motorista) {
	motorista.setId(this.id);
	motorista.setCnh(this.cnh);
	motorista.setNome(this.nome);
	motorista.setSobrenome(this.sobrenome);
	
	return motorista;
} 

public void fromMotorista(Motorista motorista) {
	this.id = motorista.getId();
	this.cnh = motorista.getCnh();
	this.nome = motorista.getNome();
	this.sobrenome = motorista.getSobrenome();
	
} 
	
	@Override
	public String toString() {
		return "requisicaoMotorista [cnh=" + cnh + ", nome=" + nome + ", sobrenome="
				+ sobrenome + "]";
	}
	
	
	}
