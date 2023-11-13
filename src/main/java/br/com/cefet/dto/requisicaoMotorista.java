package br.com.cefet.dto;

import br.com.cefet.model.Motorista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoMotorista {
	@NotNull
	private int idMotorista;
	@NotBlank
	@NotNull
	private String cnh;

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
