package br.com.cefet.model;

public class Funcionario extends Usuario{
	private String matricula;
	private String cnpj;
	private Cargo cargo;	//enum
	
	
	public Funcionario(String nomeCompleto, String email, String senha) {
		super(nomeCompleto, email, senha);
		// TODO Auto-generated constructor stub
	}
	
	public Funcionario() {
		super();
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	


	
	
}
