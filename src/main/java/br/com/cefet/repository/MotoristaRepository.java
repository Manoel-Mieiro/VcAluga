package br.com.cefet.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cefet.model.Motorista;
=======
import br.com.cefet.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f

@Repository
//O trecho abaixo serve para usar os métodos de CRUD disponibilizados pela biblioteca JpaRepository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer>{
	
<<<<<<< HEAD
}
=======
}
>>>>>>> cb7ffacdad20912bd8b98744ae45e6dede3c354f
