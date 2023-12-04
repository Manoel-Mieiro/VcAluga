package br.com.cefet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Veiculo;

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface FilialRepository extends JpaRepository<Filial, Integer>{
	 List<Filial> findByCnpjAndUf(String cnpj, String uf);
}