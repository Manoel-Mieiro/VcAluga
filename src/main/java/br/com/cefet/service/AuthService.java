package br.com.cefet.service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Usuario;
import br.com.cefet.repository.ClienteRepository;
import br.com.cefet.repository.FuncionarioRepository;
import br.com.cefet.repository.UsuarioRepository;

@Service
public class AuthService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Cliente autenticarCliente(String cpf, String senha) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isPresent() && clienteOptional.get().getSenha().equals(senha)) {
            return clienteOptional.get();
        }
        return null;
    }

    public Funcionario autenticarFuncionario(String cpf, String senha) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByCpf(cpf);
        if (funcionarioOptional.isPresent() && funcionarioOptional.get().getSenha().equals(senha)) {
            return funcionarioOptional.get();
        }
        return null;
    }
    
    public boolean verificarCredenciais(String cpf, String senha) {
        // Busca o usuário pelo email no banco de dados
        Optional<Usuario> userOptional = usuarioRepository.findByCpf(cpf);

        // Verifica se o usuário existe e se a senha está correta
        return userOptional.isPresent() && userOptional.get().getSenha().equals(senha);
    }
    
    
    public Usuario autenticarUsuario(String cpf, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(cpf);
        if (usuarioOptional.isPresent() && usuarioOptional.get().getSenha().equals(senha)) {
            return usuarioOptional.get();
        }
        return null;
    }
    
}