package br.com.cefet.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.ReservaRepository;
import br.com.cefet.repository.VeiculoRepository;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservaControllerTest {

	 @InjectMocks
	    private ReservaController reservaController;

	    @Mock
	    private VeiculoRepository veiculoRepository;

	    @Mock
	    private ReservaRepository reservaRepository;

	    @Mock
	    private HttpSession session;
	    
	    @MockBean
	    private SessaoService sessaoService;
	    @Mock
	    private RedirectAttributes redirectAttributes;
	    
	    @Autowired
	    private WebApplicationContext wac;
	    
	    private MockMvc mockMvc;


	    @Test
	    public void testCreateReserva() throws Exception {
	        // Configuração do cenário de teste
	        requisicaoReserva requisicao = new requisicaoReserva();
	        requisicao.setVeiculoId(1); // Substitua pelo ID real do veículo

	        Veiculo veiculo = new Veiculo();
	        veiculo.setId(1);
	        // Configurar outros atributos do veículo conforme necessário

	        Cliente cliente = new Cliente();
	        // Configurar outros atributos do cliente conforme necessário

	        when(veiculoRepository.findById(any())).thenReturn(Optional.of(veiculo));
	        when(session.getAttribute("cliente")).thenReturn(cliente);

	        // Executar o método a ser testado
	        ModelAndView result = reservaController.create(requisicao, new BeanPropertyBindingResult(requisicao, "requisicao"), session, redirectAttributes);

	        // Verificar o resultado
	        assertEquals("redirect:/reservas/new", result.getViewName());
	        // Adicione mais verificações conforme necessário

	        // Verificar se o veículo foi marcado como "Reservado"
	        assertEquals("Reservado", veiculo.getStatus());

	        // Verificar se a reserva foi salva
	        verify(reservaRepository, times(1)).save(any(Reserva.class));
	    }
	}
