package br.com.cefet.model;



import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;


@Entity
public class Motorista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMotorista;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String sobrenome;
	@Column(nullable = false)
	private String cnh;

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

	public Motorista() {
		super();
	}
	public Motorista(String cnh) {
		super();
		this.cnh = cnh;
	}
	
    public static boolean validarCNH(String cnh) {
    	
        if (cnh == null || !cnh.matches("\\d{11}")) {
            return false;
        }

        if (cnh.matches("(\\d)\\1{10}")) {
            return false;
        }

        String digitos = cnh.substring(0, 9);
        String dv1 = cnh.substring(9, 10);
        String dv2 = cnh.substring(10);

        int soma = 0;
        int peso = 2;

        for (int i = 8; i >= 0; i--) {
            soma += Integer.parseInt(String.valueOf(digitos.charAt(i))) * peso;
            peso++;
        }

        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        peso = 2;
        digitos = digitos + digitoVerificador1;

        for (int i = 9; i >= 0; i--) {
            soma += Integer.parseInt(String.valueOf(digitos.charAt(i))) * peso;
            peso++;
        }

        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;

        return dv1.equals(String.valueOf(digitoVerificador1)) && dv2.equals(String.valueOf(digitoVerificador2));
    }
	
}
