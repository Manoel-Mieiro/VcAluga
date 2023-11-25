package br.com.cefet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	 Optional<Cliente> findByCpf(String cpf);
}