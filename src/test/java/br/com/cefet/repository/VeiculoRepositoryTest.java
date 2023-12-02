package br.com.cefet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("Deve cadastrar um veículo no banco de dados")
	void testCreateVeiculo() {

		requisicaoVeiculo testData = new requisicaoVeiculo();

		  Filial filial = createFilial();
		

		   Veiculo veiculo = createVeiculo(testData, filial);


		assertNotNull(veiculo);
		assertNotNull(veiculo.getBranch());
		

		assertEquals(2013, veiculo.getAno());
		assertEquals(Categoria.Popular, veiculo.getCategoriaVeiculo());
		assertEquals(Paletas.Azul, veiculo.getCor());
		assertEquals(Marca.Chevrolet, veiculo.getMarcaVeiculo());
		assertEquals("Onix", veiculo.getModeloVeiculo());
		assertEquals("JLB-2580", veiculo.getPlaca());
		assertEquals(0F, veiculo.getQuilometragem());
		
		
		veiculoRepository.save(veiculo);
		
		
		Optional<Veiculo> veiculoSalvoOptional = veiculoRepository.findById(veiculo.getId());
		assertTrue(veiculoSalvoOptional.isPresent());
		Veiculo veiculoSalvo = veiculoSalvoOptional.get();
		
		assertEquals(veiculo.getAno(), veiculoSalvo.getAno());
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
