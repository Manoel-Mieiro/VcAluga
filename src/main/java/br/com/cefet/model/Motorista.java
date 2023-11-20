package br.com.cefet.model;

<<<<<<< HEAD
=======


import java.util.Set;

>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.persistence.ManyToMany;
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f

@Entity
public class Motorista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	private int id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String sobrenome;
	@Column(nullable = false)
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
=======
	private int idMotorista;
	@Column(nullable = false)
	private String cnh;
//	@ManyToMany
//	Set<Veiculo> veiculo;
	
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
=======
	public Motorista() {
		super();
	}
	public Motorista(String cnh) {
		super();
		this.cnh = cnh;
	}
	
	
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f
	
	
}
