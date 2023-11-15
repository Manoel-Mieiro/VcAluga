package br.com.cefet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cefet.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
}