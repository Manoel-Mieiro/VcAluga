package br.com.cefet.model;

public class Cliente extends Usuário{
	private String cpf;
	private int telefone;
	private String email;
	public Cliente(String nome, String sobrenome) {
		super(nome, sobrenome);
		// TODO Auto-generated constructor stub
	}
	public Cliente(String cpf, int telefone, String email) {
		super();
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
