package br.com.cefet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Motorista;
import br.com.cefet.model.Veiculo;

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer>{
	List<Motorista> findByCliente(Cliente cliente);

}

