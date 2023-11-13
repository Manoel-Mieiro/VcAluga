package br.com.cefet.model;

public class Funcionário extends Usuario{
	private String matricula;
	private Cargo cargo;	//enum
	
	public Funcionário(String nome, String sobrenome) {
		super(nome, sobrenome);
		// TODO Auto-generated constructor stub
	}

	public Funcionário(String matricula, Cargo cargo) {
		super();
		this.matricula = matricula;
		this.cargo = cargo;
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
	
	
	
}
