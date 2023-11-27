package br.com.cefet.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.ReservaRepository;
import br.com.cefet.repository.VeiculoRepository;
import jakarta.validation.Valid;

@Controller
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;

	@GetMapping("/reservas")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("reservas/index");
		List<Reserva> reservas = this.reservaRepository.findByStatus("Reservado");
		/* List<Reserva> reservas = this.reservaRepository.findAll(); */
		mv.addObject("reservas", reservas);

		return mv;
	}

	@GetMapping("/reservas/new")
	public ModelAndView novo(@RequestParam(name = "veiculoId") int veiculoId) {
		Optional<Veiculo> optional = veiculoRepository.findById(veiculoId);

		if (optional.isPresent()) {
			Veiculo veiculo = optional.get();
			ModelAndView mv = new ModelAndView("reservas/new");
			Reserva reserva = new Reserva();

			// Defina o veículo na reserva antes de acessar suas propriedades
			reserva.setVeiculo(veiculo);
			System.out.println("Categoria " + reserva.getVeiculo().getCategoriaVeiculo());
			// Agora você pode acessar a categoria do veículo
			reserva.setValorPago(veiculo.obterValorDiaria(reserva.getCategoriaVeiculo()));
			System.out.println("valor " + reserva.getValorPago());

			mv.addObject("veiculo", veiculo);
			mv.addObject("reserva", reserva);

			return mv;
		} else {
			System.out.println("Veículo não encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}

//	 O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação
//	 de dados thymeleaf
	@ModelAttribute(value = "requisicaoReserva")
	public requisicaoReserva getRequisicaoReserva() {
		return new requisicaoReserva();
	}

	@PostMapping("/reservas")
	public ModelAndView create(@Valid requisicaoReserva requisicao, BindingResult result,
			RedirectAttributes redirectAttributes) {
		Veiculo veiculo = veiculoRepository.findById(requisicao.getVeiculoId()).orElse(null);
		System.out.println("Veiculo ID: " + veiculo.getId());
		ModelAndView mv = new ModelAndView("redirect:/reservas/new");
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			// Percorre os erros de campo (field errors)
			for (FieldError error : result.getFieldErrors()) {
				System.out.println("Field: " + error.getField());
				System.out.println("Message: " + error.getDefaultMessage());

				// Adicione a mensagem de erro ao RedirectAttributes se necessário
				redirectAttributes.addFlashAttribute("error", error.getDefaultMessage());
			}

			// Percorre os erros globais
			for (ObjectError error : result.getGlobalErrors()) {
				System.out.println("Object: " + error.getObjectName());
				System.out.println("Message: " + error.getDefaultMessage());

				// Adicione a mensagem de erro ao RedirectAttributes se necessário
				redirectAttributes.addFlashAttribute("error", error.getDefaultMessage());
			}
			mv.addObject("veiculoId", requisicao.getVeiculoId());
			return mv;
		} else {
			Reserva reserva = new Reserva();
			reserva.setVeiculo(veiculo);
			System.out.println("Veiculo: " + reserva.getVeiculo());
	        veiculo.setStatus("Reservado");
	        veiculoRepository.save(veiculo);
	        System.out.println("Veiculo: " + veiculo.getStatus());
			reserva.setCategoriaVeiculo(veiculo);
			System.out.println("Categoria: " + reserva.getVeiculo().getCategoriaVeiculo());
			reserva.setModeloVeiculo(veiculo);
			reserva.setMarcaVeiculo(veiculo);
			reserva.setPlaca(veiculo);
			reserva.setCor(veiculo);
			reserva.setAno(veiculo);
			reserva.setBranch(veiculo);
//            

//			reserva.setDataReserva(requisicao.getDataReserva());
//			reserva.setDataDevolucao(requisicao.getDataDevolucao());
			reserva.setValorPago(veiculo.obterValorDiaria(requisicao.getCategoriaVeiculo()));
			reserva = requisicao.toReserva(reserva);
			reserva.setStatus("Reservado");
			LocalDate localDateReserva = reserva.getDataReserva().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			System.out.println("DATA INSERIDA: " + localDateReserva);
			System.out.println("TODAY: " + localDateReserva.isBefore(LocalDate.now()));
			
			if (localDateReserva.isBefore(LocalDate.now())) {

				mv.addObject("veiculoId", requisicao.getVeiculoId());
				return mv;
			}

			this.reservaRepository.save(reserva);

			// Redirecione para uma página de sucesso ou qualquer outra ação necessária
			mv = new ModelAndView("redirect:/contratos/new?idReserva=" + reserva.getIdReserva());

			return mv;
		}
	}

	@GetMapping("/reservas/{idReserva}")
	public ModelAndView show(@PathVariable Integer idReserva) {

		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);

		if (optional.isPresent()) {
			Reserva reserva = optional.get();
			System.out.println("Reserva: " + reserva);
			System.out.println("Veiculo: " + reserva.getVeiculo());
			ModelAndView mv = new ModelAndView("reservas/show");
			mv.addObject("reserva", reserva);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/reservas");
		}
	}

	@GetMapping("/reservas/{idReserva}/edit")
	public ModelAndView edit(@PathVariable Integer idReserva, requisicaoReserva requisicao) {
		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);

		if (optional.isPresent()) {
			System.out.printf("%d", idReserva);
			Reserva reserva = optional.get();
			requisicao.fromReserva(reserva);
			ModelAndView mv = new ModelAndView("reservas/edit");
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/reservas");
		}
	}

	@PostMapping("/reservas/{idReserva}")
	public ModelAndView update(@PathVariable Integer idReserva, @Valid requisicaoReserva requisicao,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("reservas/edit");
			return mv;
		} else {
			Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
			if (optional.isPresent()) {
				Reserva reserva = requisicao.toReserva(optional.get());
				this.reservaRepository.save(reserva);
				return new ModelAndView("redirect:/reservas/" + reserva.getIdReserva());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/reservas");
			}
		}
	}

	@PostMapping("/reservas/{idReserva}/archive")
	public ModelAndView archive(@PathVariable Integer idReserva, @Valid requisicaoReserva requisicao,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("reservas/edit");
			return mv;
		} else {
			Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
			if (optional.isPresent()) {
				Reserva reserva = optional.get();
				reserva.setStatus("Arquivado");
				this.reservaRepository.save(reserva);
				return new ModelAndView("/reservas");
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/reservas");
			}
		}
	}
	
	@GetMapping("/reservas/{idReserva}/delete")
	public String delete(@PathVariable Integer idReserva) {
		try {
			this.reservaRepository.deleteById(idReserva);
			return "redirect:/reservas";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/reservas";
		}
	}
}