package br.com.cefet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Usuario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
}