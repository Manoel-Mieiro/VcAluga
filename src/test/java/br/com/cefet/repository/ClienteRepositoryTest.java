package br.com.cefet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class ClienteRepositoryTest {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("Deve cadastrar um cliente no banco")
	void testCriarCliente() {
		Cliente cliente = createCliente();
		assertNotNull(cliente);

		assertEquals("Mountain", cliente.getNome());
		assertEquals("Tim", cliente.getSobrenome());
		assertEquals("swgkjwg09w", cliente.getSenha());
		assertEquals(Conta.Cliente, cliente.getTipo());
		assertEquals("718.462.990-63", cliente.getCpf());
		assertEquals("timmountainusa@gmail.com", cliente.getEmail());
		assertEquals("(85) 97224-5317", cliente.getTelefone());
		assertEquals("Rua Patrício Santana", cliente.getEndereco());
		assertEquals("32", cliente.getNumero());
		assertEquals("Jardim Satélite", cliente.getBairro());
		assertEquals("SP", cliente.getEstado());
		assertEquals(new Date(2001 / 12 / 02), cliente.getDataNascimento());

		clienteRepository.save(cliente);

		Optional<Cliente> optional = clienteRepository.findByCpf(cliente.getCpf());
		assertTrue(optional.isPresent());
		Cliente clienteSalvo = optional.get();

		assertEquals(cliente.getNome(), clienteSalvo.getNome());
		assertEquals(cliente.getSobrenome(), clienteSalvo.getSobrenome());
		assertEquals(cliente.getSenha(), clienteSalvo.getSenha());
		assertEquals(cliente.getTipo(), clienteSalvo.getTipo());
		assertEquals(cliente.getCpf(), clienteSalvo.getCpf());
		assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
		assertEquals(cliente.getTelefone(), clienteSalvo.getTelefone());
		assertEquals(cliente.getEndereco(), clienteSalvo.getEndereco());
		assertEquals(cliente.getNumero(), clienteSalvo.getNumero());
		assertEquals(cliente.getBairro(), clienteSalvo.getBairro());
		assertEquals(cliente.getEstado(), clienteSalvo.getEstado());
		assertEquals(cliente.getDataNascimento(), clienteSalvo.getDataNascimento());

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
		cliente.setDataNascimento(new Date(2001 / 12 / 02));

		return cliente;
	}

}
