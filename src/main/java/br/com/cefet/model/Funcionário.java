package br.com.cefet.model;

public class Funcionário extends Usuario{
	private String matrícula;
	private String telefone;
	private String email;
	private Cargo cargo;	//enum
	
	public Funcionário(String nome, String sobrenome) {
		super(nome, sobrenome);
		// TODO Auto-generated constructor stub
	}

	public Funcionário(String matrícula, String telefone, String email, Cargo cargo) {
		super();
		this.matrícula = matrícula;
		this.telefone = telefone;
		this.email = email;
		this.cargo = cargo;
	}

	public String getMatrícula() {
		return matrícula;
	}

	public void setMatrícula(String matrícula) {
		this.matrícula = matrícula;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
}
