package br.com.cefet.repository;

import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Integer>{
	List<Manutencao> findByStatus(String status);
	List<Manutencao> findByStatusAndUsuario(String status, Usuario usuario);
}
