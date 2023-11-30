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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Motorista;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.ClienteRepository;
import br.com.cefet.repository.MotoristaRepository;
import br.com.cefet.repository.ReservaRepository;
import br.com.cefet.repository.VeiculoRepository;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private MotoristaRepository motoristaRepository;


	@Autowired
	private SessaoService sessaoService;

	@GetMapping("/reservas")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("reservas/index");
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		
		if (funcionario == null && cliente == null) {
			System.out.println("Sessão vazia!");
			return new ModelAndView("redirect:/sessoes");

		} else {
			if (funcionario != null) {
				List<Reserva> reservas = this.reservaRepository.findByStatus("Reservado");
				mv.addObject("reservas", reservas);
			}
			if (cliente != null) {
				List<Reserva> reservas = this.reservaRepository.findByStatusAndCliente("Reservado", cliente);
				mv.addObject("reservas", reservas);
			}
			return mv;
		}
		/* List<Reserva> reservas = this.reservaRepository.findAll(); */
	}

	@GetMapping("/reservas/new")
	public ModelAndView novo(@RequestParam(name = "veiculoId") int veiculoId) {
		Optional<Veiculo> optional = veiculoRepository.findById(veiculoId);
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		List<Motorista> motorista = motoristaRepository.findByCliente(cliente);
		System.out.println("Motoristas - " + motorista);
		if (motorista.isEmpty()) {
			System.out.println("Cadastre um motorista para incluir no contrato");
			return new ModelAndView("redirect:/motoristas");
		}
		if (cliente == null) {
			System.out.println("Cliente nulo!");
			return new ModelAndView("redirect:/sessoes");
		} else {
			if (optional.isPresent()) {
				Veiculo veiculo = optional.get();
				ModelAndView mv = new ModelAndView("reservas/new");
				mv.addObject("cliente", cliente);
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
	}

//	 O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação
//	 de dados thymeleaf
	@ModelAttribute(value = "requisicaoReserva")
	public requisicaoReserva getRequisicaoReserva() {
		return new requisicaoReserva();
	}

	@PostMapping("/reservas")
	public ModelAndView create(@Valid requisicaoReserva requisicao, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Veiculo veiculo = veiculoRepository.findById(requisicao.getVeiculoId()).orElse(null);
		System.out.println("Veiculo ID: " + veiculo.getId());
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		System.out.println("Cliente logado: " + cliente.getNome() + " " + cliente.getSobrenome());
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
			reserva.setCliente(cliente);
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
			this.clienteRepository.save(cliente);
			this.reservaRepository.save(reserva);

			// Redirecione para uma página de sucesso ou qualquer outra ação necessária
			mv = new ModelAndView("redirect:/contratos/new?idReserva=" + reserva.getIdReserva());

			return mv;
		}
	}

	@GetMapping("/reservas/{idReserva}")
	public ModelAndView show(@PathVariable Integer idReserva, HttpSession session) {

		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		if (optional.isPresent() && cliente != null) {
			Reserva reserva = optional.get();
			System.out.println("Reserva: " + reserva);
			System.out.println("Veiculo: " + reserva.getVeiculo());
			ModelAndView mv = new ModelAndView("reservas/show");
			mv.addObject("cliente", cliente);
			mv.addObject("reserva", reserva);
			return mv;
		} else if (cliente == null) {
			System.out.println("Cliente nulo!");
			return new ModelAndView("redirect:/sessoes");
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

	@GetMapping("/reservas/{idReserva}/archive")
	public String archive(@PathVariable Integer idReserva) {

		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
		if (optional.isPresent()) {
			Reserva reserva = optional.get();
			reserva.setStatus("Arquivado");
			Veiculo veiculo = reserva.getVeiculo();
			veiculo.setStatus("Disponível");
			veiculoRepository.save(veiculo);
			this.reservaRepository.save(reserva);
			return "redirect:/veiculos";
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return "redirect:/reservas";
		}
	}

	@GetMapping("/reservas/{idReserva}/delete")
	public String delete(@PathVariable Integer idReserva) {
		Optional<Reserva> optional = this.reservaRepository.findById(idReserva);
		if (optional.isPresent()) {
			Reserva reserva = optional.get();
			Veiculo veiculo = reserva.getVeiculo();
			veiculoRepository.save(veiculo);
			veiculo.setStatus("Disponível");
		}
		try {
			this.reservaRepository.deleteById(idReserva);
			return "redirect:/reservas";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/reservas";
		}
	}
}