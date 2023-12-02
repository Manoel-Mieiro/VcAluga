package br.com.cefet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Veiculo;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class VeiculoRepositoryTest {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	FilialRepository filialRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository; 
	
	@Test
	void testCreateVeiculo() {
		// Criar uma instância de requisicaoVeiculo com dados de teste
		requisicaoVeiculo testData = new requisicaoVeiculo(/*...dados de teste...*/);

		  Filial filial = createFilial();
		
		// Chamar o método createVeiculo para criar um veículo
		   Veiculo veiculo = createVeiculo(testData, filial);

		// Verificar se o veículo foi criado corretamente
		assertNotNull(veiculo);
		assertNotNull(veiculo.getBranch());
		
		// Adicione mais verificações conforme necessário para garantir que o veículo foi criado corretamente
		assertEquals(2013, veiculo.getAno());
		assertEquals(Categoria.Popular, veiculo.getCategoriaVeiculo());
		assertEquals(Paletas.Azul, veiculo.getCor());
		assertEquals(Marca.Chevrolet, veiculo.getMarcaVeiculo());
		assertEquals("Onix", veiculo.getModeloVeiculo());
		assertEquals("JLB-2580", veiculo.getPlaca());
		assertEquals(0F, veiculo.getQuilometragem());
		
		// Salvar o veículo no banco de dados (se seu repositório estiver configurado para salvar automaticamente, você pode pular esta parte)
		veiculoRepository.save(veiculo);
		
		// Recuperar o veículo do banco de dados para garantir que foi salvo corretamente
		Optional<Veiculo> veiculoSalvoOptional = veiculoRepository.findById(veiculo.getId());
		assertTrue(veiculoSalvoOptional.isPresent());
		Veiculo veiculoSalvo = veiculoSalvoOptional.get();
		
		// Adicionar mais verificações se necessário
		assertEquals(veiculo.getAno(), veiculoSalvo.getAno());
		// Adicione verificações para outros atributos
		
		// Lembre-se de lidar com o cleanup, se necessário, para evitar efeitos colaterais nos outros testes
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
        filialRepository.save(filial); // Salvar no banco de dados
        return filial;
    }
	
	   private Veiculo createVeiculo(requisicaoVeiculo data, Filial filial) {
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
	        return veiculo;
	    }

}
