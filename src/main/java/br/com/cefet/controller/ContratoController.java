package br.com.cefet.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.dto.requisicaoEstacao;
import br.com.cefet.dto.requisicaoManutencao;
import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Contrato;
import br.com.cefet.model.Estacao;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.ContratoRepository;
import br.com.cefet.repository.EstacaoRepository;
import br.com.cefet.repository.FilialRepository;
import br.com.cefet.repository.ManutencaoRepository;
import br.com.cefet.repository.ReservaRepository;
import br.com.cefet.repository.VeiculoRepository;
import jakarta.validation.Valid;

@Controller
public class ContratoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private FilialRepository filialRepository;
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;

	@GetMapping("/contratos")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("contratos/index");

		List<Contrato> contratos = this.contratoRepository.findAll();
		mv.addObject("contratos", contratos);

		return mv;
	}

	@GetMapping("/contratos/new")
	public ModelAndView novo(@RequestParam(name = "idReserva") int idReserva) {
		Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);

//		if (optionalReserva.isPresent()) {
//			Reserva reserva = optionalReserva.get();
//
//			List<Estacao> estacoes = estacaoRepository.findAll(); // Obtém a lista de todas as estações

			ModelAndView mv = new ModelAndView("contratos/new");
//			mv.addObject("veiculo", veiculo);
//			mv.addObject("estacoes", estacoes); // Adiciona a lista de estações ao modelo

			return mv;
//		} else {
//			System.out.println("Veículo não encontrado.");
//			return new ModelAndView("redirect:/veiculos");
//		}
	}
//
////	 O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação
////	 de dados thymeleaf
//	@ModelAttribute(value = "requisicaoManutencao")
//	public requisicaoManutencao getRequisicaoManutencao() {
//		return new requisicaoManutencao();
//	}
//
//	@PostMapping("/manutencoes")
//	public ModelAndView create(@ModelAttribute requisicaoManutencao requisicao, BindingResult result) {
//	    ModelAndView mv = new ModelAndView("manutencoes/new");
//
//	    if (result.hasErrors()) {
//	        System.out.println("\n**********************Invalid Input Found**************************\n");
//	        return mv;
//	    } else {
//	        Veiculo veiculo = veiculoRepository.findById(requisicao.getVeiculoId()).orElse(null);
//	        System.out.printf("ID VEICULO - %d%n", requisicao.getVeiculoId());
//
//	        int estacaoId = 0;
//	        try {
//	            estacaoId = Integer.parseInt(requisicao.getEstacaoId());
//	        } catch (NumberFormatException e) {
//	            e.printStackTrace();
//	        }
//
//	        Estacao estacao = estacaoRepository.findById(estacaoId).orElse(null);
//	        System.out.printf("ID ESTACAO - %d%n", estacaoId);
//
//	        if (veiculo != null && estacao != null) {
//	            Manutencao manutencao = new Manutencao();
//	            mv = new ModelAndView("redirect:/manutencoes/" + manutencao.getIdManutencao());
//	            
//	            manutencao.setEstacao(estacao);
//	            manutencao.setVeiculo(veiculo);
//	            
//	    
//	            manutencao = requisicao.toManutencao(manutencao);
//	            
//	            System.out.println("Data de entrada recebida: " + manutencao.getDataEntrada());
//	            System.out.println("Data de saída recebida: " + manutencao.getDataSaida());
//	            
//	      
//	            // Salve a manutenção no banco de dados
//	            
//	            this.manutencaoRepository.save(manutencao);
//
//
//	            return mv;
//	        } else {
//	            System.out.println("Não foi possível realizar agendamento.");
//	            return new ModelAndView("redirect:/veiculos");
//	        }
//	    }
//	}
//
//	@GetMapping("/manutencoes/{idManutencao}")
//	public ModelAndView show(@PathVariable Integer idManutencao) {
//
//		Optional<Manutencao> optional = this.manutencaoRepository.findById(idManutencao);
//
//		if (optional.isPresent()) {
//			Manutencao manutencao = optional.get();
//
//			ModelAndView mv = new ModelAndView("manutencoes/show");
//			mv.addObject("manutencao", manutencao);
//			return mv;
//		} else {
//			System.out.println("Registro não consta no banco ou não foi encontrado.");
//			return new ModelAndView("redirect:/manutencoes");
//		}
//	}
//
//	@GetMapping("/manutencoes/{idManutencao}/delete")
//	public String delete(@PathVariable Integer idManutencao) {
//		try {
//			this.manutencaoRepository.deleteById(idManutencao);
//			return "redirect:/manutencoes";
//		} catch (EmptyResultDataAccessException e) {
//			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
//			return "redirect:/manutencoes";
//		}
//	}
}