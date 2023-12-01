package br.com.cefet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.cefet.model.Filial;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.VeiculoRepository;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private VeiculoRepository veiculoRepository;

	 private static final Logger logger = LoggerFactory.getLogger(VeiculoControllerTest.class);
	
	 @Test
	    void testCriarVeiculo() throws Exception {
	        String requestBody = "{ \"idFilial\": 8, \"modeloVeiculo\": \"Taos\", \"marcaVeiculo\": \"Volkswagen\", \"quilometragem\": \"0\", \"cor\": \"Branco\", \"categoriaVeiculo\": \"Economico\", \"ano\": \"2015\", \"placa\": HPT-2642}";

	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestBody))
	                .andReturn();
	        
	        int status = result.getResponse().getStatus();
	        logger.info("Status da resposta: {}", status);

	        verify(veiculoRepository).save(any(Veiculo.class));

	        logger.info("Teste de criação de veículo concluído.");
	       	        
	        assertEquals(302, status); // 302 Found (redirect)

	    }

	 @Test
	 void testVeiculoCriadoNoBancoDeDados() throws Exception {
	     String requestBody = "{ \"idFilial\": 8, \"modeloVeiculo\": \"Taos\", \"marcaVeiculo\": \"Volkswagen\", \"quilometragem\": \"0\", \"cor\": \"Branco\", \"categoriaVeiculo\": \"Economico\", \"ano\": \"2015\"}";

	     // Realiza a criação do veículo
	     MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(requestBody))
	             .andReturn();

	     int status = result.getResponse().getStatus();
	     logger.info("Status da resposta: {}", status);

	     // Verifica se houve redirecionamento
	     assertEquals(302, status); // 302 Found (redirect)

	     // Obtém o URL redirecionado
	     String redirectedUrl = result.getResponse().getHeader("Location");
	     assertNotNull(redirectedUrl);

	     // Extrai o ID do URL redirecionado
	     String[] parts = redirectedUrl.split("/");
	     int veiculoId = Integer.parseInt(parts[parts.length - 1]);

	     // Ajuste 1: Obtém o veículo criado no banco de dados usando o ID extraído
	     Veiculo veiculoCriado = veiculoRepository.findById(veiculoId).orElse(null);
	     assertNotNull(veiculoCriado);

	     // Ajuste 2: Adiciona logs para cada atributo do veículo
	     logger.info("ID do veículo: {}", veiculoCriado.getId());
	     logger.info("Modelo do veículo: {}", veiculoCriado.getModeloVeiculo());
	     logger.info("Marca do veículo: {}", veiculoCriado.getMarcaVeiculo());
	     logger.info("Quilometragem do veículo: {}", veiculoCriado.getQuilometragem());
	     logger.info("Cor do veículo: {}", veiculoCriado.getCor());
	     logger.info("Categoria do veículo: {}", veiculoCriado.getCategoriaVeiculo());
	     logger.info("Ano do veículo: {}", veiculoCriado.getAno());

	     logger.info("Teste de criação de veículo concluído.");
	 }


	 

}
