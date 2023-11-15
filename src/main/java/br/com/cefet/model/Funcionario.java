package br.com.cefet.model;

public class Funcionario extends Usuario{
	private String matricula;
	private Cargo cargo;  //enum
	private String filial;
	
	public Funcionario(String nome, String sobrenome) {
		super(nome, sobrenome);
		// TODO Auto-generated constructor stub
	}

	public Funcionario(String matricula, Cargo cargo, String filial) {
		super();
		this.matricula = matricula;
		this.cargo = cargo;
		this.filial = filial;
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

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	
}
