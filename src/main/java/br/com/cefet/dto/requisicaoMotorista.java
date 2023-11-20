package br.com.cefet.dto;

import br.com.cefet.model.Motorista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoMotorista {
	@NotNull
<<<<<<< HEAD
	private int id;
	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	private String sobrenome;
=======
	private int idMotorista;
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f
	@NotBlank
	@NotNull
	private String cnh;

<<<<<<< HEAD
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
=======
	public int getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(int idMotorista) {
		this.idMotorista = idMotorista;
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

<<<<<<< HEAD
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
=======
	public Motorista toMotorista() {
		Motorista Motorista = new Motorista();
		Motorista.setCnh(this.cnh);
		return Motorista;
	}

	public Motorista toMotorista(Motorista Motorista) {
		Motorista.setCnh(this.cnh);

		return Motorista;
	}

	public void fromMotorista(Motorista Motorista) {
		this.cnh = Motorista.getCnh();
	}

}
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f
