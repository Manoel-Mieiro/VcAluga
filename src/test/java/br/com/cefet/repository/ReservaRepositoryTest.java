package br.com.cefet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class ReservaRepositoryTest {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	FilialRepository filialRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository; 
	
	@Autowired
	ReservaRepository reservaRepository; 
	
	@Autowired
	ClienteRepository clienteRepository; 
	
	@Test
	@DisplayName("Deve reservar um veículo indicado")
	void testCreateReserva() {
		  Filial filial = createFilial();
			
		   requisicaoVeiculo testData = new requisicaoVeiculo();
		   Veiculo veiculo = createVeiculo(filial);
		   Cliente cliente = createCliente();
		   
		   Reserva reserva = createReserva(veiculo, cliente);
		   
		   assertNotNull(reserva.getVeiculo());
		   assertNotNull(veiculo.getBranch());
		   assertNotNull(reserva.getCliente());
		   
		   assertEquals("Corrente", reserva.getStatus());
		   assertEquals(200F, reserva.getValorPago());
		   assertEquals(new Date(2023/12/04), reserva.getDataReserva());
		   assertEquals(new Date(2023/12/10), reserva.getDataDevolucao());
		   
		   reservaRepository.save(reserva);
		   
		   Optional<Reserva> optional = reservaRepository.findById(reserva.getIdReserva());
		   assertTrue(optional.isPresent());
		   Reserva reservaSalva = optional.get();
		   assertEquals(reserva.getValorPago(), reservaSalva.getValorPago());
		   assertEquals(reserva.getVeiculo(), reservaSalva.getVeiculo());
		   System.out.println(reserva.getVeiculo() + " " + reservaSalva.getVeiculo());
		   assertEquals(reserva.getCliente(), reservaSalva.getCliente());
		   
	}
	
	private Filial createFilial() {
        Filial filial = new Filial();
        filial.setCnpj("99.179.944/0001-35");
        filial.setCep("68906-184");
        filial.setEndereco("Avenida Brasil");
        filial.setComplemento("Galpão 6");
        filial.setNumero((short)13);
        filial.setBairro("São José");
        filial.setUf("AP");
        filial.setCota(21F);
        filial.setQuantidadeVeiculos(65);
        filialRepository.save(filial); 
        return filial;
    }
	
	   private Veiculo createVeiculo(Filial filial) {
		   
	        Veiculo veiculo = new Veiculo();
	        veiculo.setBranch(filial);
	        veiculo.setAno(2013);
	        veiculo.setCategoriaVeiculo(Categoria.Popular);
	        veiculo.setCor(Paletas.Azul);
	        veiculo.setMarcaVeiculo(Marca.Chevrolet);
	        veiculo.setModeloVeiculo("Onix");
	        veiculo.setPlaca("JLB-2580");
	        veiculo.setQuilometragem(0F);
	        veiculo.setStatus("Disponível");
	        veiculoRepository.save(veiculo);
	        
	        return veiculo;
	    }
	   
	   
	   private Reserva createReserva(Veiculo veiculo, Cliente cliente) {
		   Reserva reserva = new Reserva();
		   
		   reserva.setVeiculo(veiculo);
		   reserva.setCliente(cliente);
		   reserva.setStatus("Corrente");
		   reserva.setValorPago(200F);
		   reserva.setDataReserva(new Date(2023/12/04));
		   reserva.setDataDevolucao(new Date(2023/12/10));
		

	        return reserva;
	    }
	   
	   private Cliente createCliente() {
	        Cliente cliente = new Cliente();
	       cliente.setNome("Mountain");
	       cliente.setSobrenome("Tim");
	       cliente.setSenha("swgkjwg09w");
	       cliente.setTipo(Conta.Cliente);
	       cliente.setCpf("718.462.990-63");
	       cliente.setEmail("timmountainusa@gmail.com");
	       cliente.setTelefone("(85) 97224-5317");
	       cliente.setEndereco("Rua Patrício Santana");
	       cliente.setNumero("32");
	       cliente.setBairro("Jardim Satélite");
	       cliente.setCidade("São José dos Campos");
	       cliente.setEstado("SP");
	       cliente.setDataNascimento(new Date(2001/12/02));
	       clienteRepository.save(cliente);
	        
	       return cliente;
	    }


}
