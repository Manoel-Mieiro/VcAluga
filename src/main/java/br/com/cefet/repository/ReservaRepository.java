
  package br.com.cefet.repository;
  
  import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
  
  import br.com.cefet.model.Reserva;

  
  public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
	  List<Reserva> findByStatus(String status);
  }
 