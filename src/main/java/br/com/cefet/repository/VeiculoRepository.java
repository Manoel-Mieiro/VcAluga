package br.com.cefet.repository;

import br.com.cefet.model.Veiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
	 List<Veiculo> findByStatus(String status); 
	 List<Veiculo> findByStatusAndEmManutencao(String status, boolean emManutencao);
	 @Query("SELECT COUNT(v) > 0 FROM Veiculo v WHERE v.id = :veiculoId AND v.emManutencao = true")
	    boolean isVeiculoEmManutencao(@Param("veiculoId") int id);
}
