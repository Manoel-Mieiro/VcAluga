package br.com.cefet.service;

import org.springframework.stereotype.Service;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Usuario;
import jakarta.servlet.http.HttpSession;

@Service
public class SessaoService {
	 public void armazenarDadosCliente(HttpSession session, Cliente cliente) {
	        session.setAttribute("clienteLogado", cliente);
	    }

	    public Cliente obterClienteDaSessao(HttpSession session) {
	        return (Cliente) session.getAttribute("clienteLogado");
	    }

	    
	    public void armazenarDadosFuncionario(HttpSession session, Funcionario funcionario) {
	        session.setAttribute("funcionarioLogado", funcionario);
	    }

	    public Funcionario obterFuncionarioDaSessao(HttpSession session) {
	        return (Funcionario) session.getAttribute("funcionarioLogado");
	    }

	    
	    public void armazenarDadosSessao(HttpSession session,Usuario usuario) {
	            session.setAttribute("usuarioLogado", usuario);
	    }
	    
	    
	    public Usuario obterDadosSessao(HttpSession session, Conta conta) {
	    	 if (conta == Conta.Cliente) {
	    		 return (Cliente) session.getAttribute("clienteLogado");
	    	 } else if (conta == Conta.Funcionário) {
	    		 return (Funcionario) session.getAttribute("funcionarioLogado");
	    	 } else {
	    		 return null;
	    	 }
	    	 
	    }
	    
	    public void limparDadosSessao(HttpSession session, Conta conta) {
	        if (conta == Conta.Cliente) {
	            session.removeAttribute("clienteLogado");
	        } else if (conta == Conta.Funcionário) {
	            session.removeAttribute("funcionarioLogado");
	        }

	        session.invalidate();
	    }

	    
}
