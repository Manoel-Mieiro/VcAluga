package br.com.cefet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cefet.model.Contrato;

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{
	
}