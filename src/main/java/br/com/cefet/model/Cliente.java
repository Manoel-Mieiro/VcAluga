package br.com.cefet.model;

public class Cliente extends Usuario{
	private String cpf;
	private String telefone;
	private String email;
	
	
	public Cliente() {
		super();
	}
	public Cliente(String nome, String sobrenome) {
		super(nome, sobrenome);
	}
	public Cliente(String cpf, String telefone, String email) {
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
	
	
	
}
