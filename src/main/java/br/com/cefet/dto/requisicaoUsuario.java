package br.com.cefet.dto;

import br.com.cefet.model.Usuario;
import br.com.cefet.model.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
	private String telefone;
	@NotBlank
	@NotNull
	private String cpf;
	
	
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
			+ ", senha=" + senha + ", telefone=" + telefone + ", cpf=" + cpf
			+ "]";
}

public Conta getTipo() {
    return this.tipo;
}

public void setTipo(Conta tipo) {
    this.tipo = tipo;
}



	}
