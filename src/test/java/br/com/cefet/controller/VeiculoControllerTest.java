package br.com.cefet.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.VeiculoRepository;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VeiculoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private VeiculoRepository veiculoRepository;

	@InjectMocks
	private VeiculoController veiculoController;

	@Test
	public void testCadastroVeiculo() throws Exception {

		final Logger log = LoggerFactory.getLogger(VeiculoControllerTest.class);

		String requestContent = "{{\r\n" + "  \"branchId\": 1,\r\n" + "  \"placa\": \"IAE-8335\",\r\n"
				+ "  \"marcaVeiculo\": \"Toyota\",\r\n" + "  \"modeloVeiculo\": \"Onix Plus\",\r\n"
				+ "  \"categoriaVeiculo\": \"Popular\",\r\n" + "  \"quilometragem\": 0.0,\r\n"
				+ "  \"cor\": \"Prata\",\r\n" + "  \"ano\": 2022\r\n" + "}\r\n" + "}";

		log.info("Request Content: {}", requestContent);

		mockMvc.perform(post("/veiculos").contentType("application/json").content(requestContent))
				.andExpect(status().isOk()).andExpect(view().name("veiculos/new"));
	}

	/*
	 * @Test public void testDetalhesVeiculo() throws Exception {
	 * when(veiculoRepository.findById(any())).thenReturn(Optional.of(new
	 * Veiculo()));
	 * 
	 * mockMvc.perform(get("/veiculos/{id}", 1)) .andExpect(status().isOk())
	 * .andExpect(view().name("veiculos/show"))
	 * .andExpect(model().attributeExists("veiculo"));
	 * 
	 * // Verifica se o método findById foi chamado com o ID correto
	 * verify(veiculoRepository).findById(1); }
	 */

}
