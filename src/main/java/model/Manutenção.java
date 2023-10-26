package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Manutenção {
	// Bloco do Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Atributos
	private int id;
	/* usar Date para */
	private String dataEntrada;
	private String dataSaída;	
	private Estação estação;	//objeto do tipo estação
}
