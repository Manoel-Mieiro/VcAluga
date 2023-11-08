package br.com.cefet.repository;

import br.com.cefet.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{

	Veiculo findByPlaca(String placa);
	
}
