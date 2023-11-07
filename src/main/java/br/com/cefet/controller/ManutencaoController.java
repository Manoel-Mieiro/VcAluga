package br.com.cefet.controller;

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

import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Estacao;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.EstacaoRepository;
import br.com.cefet.repository.ManutencaoRepository;
import br.com.cefet.repository.VeiculoRepository;
import jakarta.validation.Valid;

@Controller
public class ManutencaoController {
	@Autowired
	private ManutencaoRepository manutencaoRepository;
	@Autowired
	private EstacaoRepository estacaoRepository;
	@Autowired
	private VeiculoRepository veiculoRepository; 

	@GetMapping("/manutencoes")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("manutencoes/index");
		
		List<Manutencao> manutencoes = this.manutencaoRepository.findAll();
		mv.addObject("manutencoes", manutencoes);

		return mv;
	}

	@GetMapping("/manutencoes/new")
	public ModelAndView novo(/*@RequestParam(name="idEstacao") int idEstacao,*/ @RequestParam(name = "veiculoId") int veiculoId) {
		Optional<Veiculo> optional = veiculoRepository.findById(veiculoId);
//		Optional<Estacao> optional2 = estacaoRepository.findById(idEstacao);

		if (optional.isPresent()/* && optional2.isPresent()*/) {
			Veiculo veiculo = optional.get();
//			Estacao estacao = optional2.get();
			ModelAndView mv = new ModelAndView("manutencoes/new");
			mv.addObject("veiculo", veiculo);
//			mv.addObject("estacao", estacao);
			return mv;
		} else {
			System.out.println("Veículo não encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}
	


////	 O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação
////	 de dados thymeleaf
//	@ModelAttribute(value = "requisicaoReserva")
//	public requisicaoReserva getRequisicaoReserva() {
//		return new requisicaoReserva();
//	}
//	
//    @PostMapping("/reservas")
//    public ModelAndView create(@ModelAttribute requisicaoReserva requisicao) {
//    	Veiculo veiculo = veiculoRepository.findById(requisicao.getVeiculoId()).orElse(null);
//        if (veiculo != null) {
//        	Reserva reserva = new Reserva();
//           	ModelAndView mv = new ModelAndView("redirect:/reservas/" + reserva.getIdReserva());
//            reserva.setVeiculo(veiculo);
//            reserva.setCategoriaVeiculo(veiculo);
//            reserva.setModeloVeiculo(veiculo);
//            reserva.setMarcaVeiculo(veiculo);
//            reserva.setPlaca(veiculo);
//            reserva.setCor(veiculo);
//            reserva.setAno(veiculo);
//            reserva.setFilial(veiculo);
//            
////            Reserva reserva = requisicao.toReserva();
//            reserva.setDataReserva(requisicao.getDataReserva());
//            reserva.setDataDevolucao(requisicao.getDataDevolucao());
//            reserva.setValorPago(requisicao.getValorPago());
//
//            // Salve a reserva no banco de dados
//            reservaRepository.save(reserva);
//
//            // Redirecione para uma página de sucesso ou qualquer outra ação necessária
//            return mv;
//        } else {
//            // Trate o caso em que o veículo não foi encontrado
//        	System.out.println("Registro não consta no banco ou não foi encontrado.");
//            return new ModelAndView ("redirect:/reservas/new");
//        }
//    }
//	
//	@GetMapping("/reservas/{idReserva}")
//	public ModelAndView show(@PathVariable Integer idReserva) {
//
//		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
//
//		if (optional.isPresent()) {
//			Reserva reserva = optional.get();
//
//			ModelAndView mv = new ModelAndView("reservas/show");
//			mv.addObject("reserva", reserva);
//			return mv;
//		} else {
//			System.out.println("Registro não consta no banco ou não foi encontrado.");
//			return new ModelAndView("redirect:/reservas");
//		}
//	}
//
//	@GetMapping("/reservas/{idReserva}/edit")
//	public ModelAndView edit(@PathVariable Integer idReserva, requisicaoReserva requisicao) {
//		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
//
//		if (optional.isPresent()) {
//			System.out.printf("%d", idReserva);
//			Reserva reserva = optional.get();
//			requisicao.fromReserva(reserva);
//			ModelAndView mv = new ModelAndView("reservas/edit");
//			return mv;
//		} else {
//			System.out.println("Registro não consta no banco ou não foi encontrado.");
//			return new ModelAndView("redirect:/reservas");
//		}
//	}
//
//	@PostMapping("/reservas/{idReserva}")
//	public ModelAndView update(@PathVariable Integer idReserva, @Valid requisicaoReserva requisicao,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			System.out.println("\n**********************Invalid Input Found**************************\n");
//
//			ModelAndView mv = new ModelAndView("reservas/edit");
//			return mv;
//		} else {
//			Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
//			if (optional.isPresent()) {
//				Reserva reserva = requisicao.toReserva(optional.get());
//				this.reservaRepository.save(reserva);
//				return new ModelAndView("redirect:/reservas/" + reserva.getIdReserva());
//			} else {
//				System.out.println("Registro não consta no banco ou não foi encontrado.");
//				return new ModelAndView("redirect:/reservas");
//			}
//		}
//	}
//
//	@GetMapping("/reservas/{idReserva}/delete")
//	public String delete(@PathVariable Integer idReserva) {
//		try {
//			this.reservaRepository.deleteById(idReserva);
//			return "redirect:/reservas";
//		} catch (EmptyResultDataAccessException e) {
//			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
//			return "redirect:/reservas";
//		}
//	}
}