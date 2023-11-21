package br.com.cefet.dto;

import br.com.cefet.model.Usuario;
import br.com.cefet.model.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class requisicaoUsuario {
	@NotNull
	private int id;
	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	private String sobrenome;
	@NotBlank
	@NotNull
	private String email;
	private Conta tipo;
	@NotBlank
	@NotNull
	private String senha;
	@NotBlank
	@NotNull
	@Pattern(regexp = "\\(\\d{2}\\)\\s9\\s\\d{4}-\\d{4}", message = "Formato de Telefone inválido")
	private String telefone;
	@NotBlank
	@NotNull
	private String cpf;
	
	
	
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Conta getTipo() {
		return tipo;
	}

	public void setTipo(Conta tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setSobrenome(this.sobrenome);
		usuario.setEmail(this.email);
		usuario.setTipo(this.tipo);
		usuario.setSenha(this.senha);
		usuario.setTelefone(this.telefone);
		usuario.setCpf(this.cpf);
		
		
		return usuario;
	} 

public Usuario toUsuario(Usuario usuario) {
	usuario.setNome(this.nome);
	usuario.setSobrenome(this.sobrenome);
	usuario.setEmail(this.email);
	usuario.setTipo(this.tipo);
	usuario.setSenha(this.senha);
	usuario.setTelefone(this.telefone);
	usuario.setCpf(this.cpf);
	
	
	return usuario;
} 

public void fromUsuario(Usuario usuario) {
	this.nome = usuario.getNome();
	this.sobrenome = usuario.getSobrenome();
	this.email = usuario.getEmail();
	this.tipo = usuario.getTipo();
	this.senha = usuario.getSenha();
	this.telefone = usuario.getTelefone();
	this.cpf = usuario.getCpf();
	}

@Override
public String toString() {
	return "requisicaoUsuario [nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", tipo=" + tipo
			+ ", senha=" + senha + ", telefone=" + telefone + ", cpf=" + cpf + "]";
}



	}
