package br.com.cefet.dto;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Motorista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoMotorista {
	@NotNull
	private int idMotorista;
	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	private String sobrenome;
	@NotBlank
	@NotNull
	private String cnh;
	private Cliente cliente;



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public int getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(int idMotorista) {
		this.idMotorista = idMotorista;

	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}


public Motorista toMotorista() {
		Motorista motorista = new Motorista();
		motorista.setIdMotorista(this.idMotorista);
		motorista.setCnh(this.cnh);
		motorista.setNome(this.nome);
		motorista.setSobrenome(this.sobrenome);
		
		return motorista;
	} 

//Metodo toVeiculo para update de um veiculo existente
public Motorista toMotorista(Motorista motorista) {
	motorista.setIdMotorista(this.idMotorista);
	motorista.setCnh(this.cnh);
	motorista.setNome(this.nome);
	motorista.setSobrenome(this.sobrenome);
	
	return motorista;
} 

public void fromMotorista(Motorista motorista) {
	this.idMotorista = motorista.getIdMotorista();
	this.cnh = motorista.getCnh();
	this.nome = motorista.getNome();
	this.sobrenome = motorista.getSobrenome();
	
}

@Override
public String toString() {
	return "requisicaoMotorista [nome=" + nome + ", sobrenome=" + sobrenome + ", cnh=" + cnh + ", cliente=" + cliente
			+ "]";
}


	
}