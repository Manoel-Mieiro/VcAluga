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

import br.com.cefet.dto.requisicaoContrato;
import br.com.cefet.dto.requisicaoEstacao;
import br.com.cefet.dto.requisicaoManutencao;
import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Contrato;
import br.com.cefet.model.Estacao;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Motorista;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.ClienteRepository;
import br.com.cefet.repository.ContratoRepository;
import br.com.cefet.repository.EstacaoRepository;
import br.com.cefet.repository.FilialRepository;
import br.com.cefet.repository.ManutencaoRepository;
import br.com.cefet.repository.MotoristaRepository;
import br.com.cefet.repository.ReservaRepository;
import br.com.cefet.repository.VeiculoRepository;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;
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
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private MotoristaRepository motoristaRepository;


	@Autowired
	private SessaoService sessaoService;

	@GetMapping("/contratos")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("contratos/index");
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		if (cliente != null) {
			List<Contrato> contratos = this.contratoRepository.findByClienteAndStatus(cliente, "Corrente");
			List<Motorista> motoristas = this.motoristaRepository.findAll();
			
			mv.addObject("cliente", cliente);
			mv.addObject("contratos", contratos);
			mv.addObject("motoristas", motoristas);
			return mv;
			} 
			else {
				Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
				if (funcionario != null) {
					List<Contrato> contratos = this.contratoRepository.findByStatus("Corrente");
					List<Motorista> motoristas = this.motoristaRepository.findAll();
					mv.addObject("cliente", cliente);
					mv.addObject("contratos", contratos);
					mv.addObject("motoristas", motoristas);
					return mv;
			}
			System.out.println("Não há contratos acossiados!");
			return new ModelAndView("redirect:/sessoes");
		}
		
			

		
	}

	@GetMapping("/contratos/new")
	public ModelAndView novo(@RequestParam(name = "idReserva") int idReserva) {
		Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		System.out.println("Reserva encontrada no método 'novo': " + optionalReserva);
		if (cliente == null) {
			System.out.println("Cliente nulo!");
			return new ModelAndView("redirect:/sessoes");
		} else {
			if (optionalReserva.isPresent()) {
				Reserva reserva = optionalReserva.get();
				System.out.println("Veiculo encontrado no método 'novo': " + reserva.getVeiculo());
				requisicaoContrato requisicao = new requisicaoContrato();
				// requisicao.preencherComReserva(reserva);
				requisicao.setDataEmissao(java.sql.Date.valueOf(LocalDate.now()));

				ModelAndView mv = new ModelAndView("contratos/new");
				mv.addObject("cliente", cliente);
				mv.addObject("motoristas", motoristaRepository.findByCliente(cliente));
				mv.addObject("requisicaoContrato", requisicao); // Adicione esta linha
				mv.addObject("reserva", reserva);
				mv.addObject("veiculo", reserva.getVeiculo());
				System.out.println("Veiculo encontrado no método 'novo': " + reserva.getVeiculo());
				mv.addObject("filial", reserva.getBranch());
				System.out.printf("%n%s%n%n: ", requisicao.getFilial());
				return mv;
			} else {
				System.out.println("Reserva não encontrada.");
				return new ModelAndView("redirect:/veiculos");
			}
		}
	}

//	 O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação
//	 de dados thymeleaf
	@ModelAttribute(value = "requisicaoContrato")
	public requisicaoContrato getRequisicaoContrato() {
		return new requisicaoContrato();
	}

	@PostMapping("/contratos")
	public ModelAndView create(@Valid requisicaoContrato requisicao, HttpSession session,BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("contratos/new");
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		System.out.println("Cliente logado: " + cliente.getNome() + " " + cliente.getSobrenome());
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

			return mv;
		} else {
			Reserva reserva = reservaRepository.findById(requisicao.getIdReserva()).orElse(null);
			System.out.println("Reserva encontrada no método 'create': " + requisicao.getReserva());

			if (reserva != null) {
				Contrato contrato = new Contrato();
				mv = new ModelAndView("redirect:/contratos/" + contrato.getIdContrato());
				contrato.setCliente(cliente);
				contrato.setReserva(reserva);
				contrato.setStatus("Corrente");
				contrato.setMotorista(requisicao.getMotorista());
				contrato.setVeiculo(reserva.getVeiculo());
				contrato.setFilial(reserva.getVeiculo().getBranch());
				System.out.println("Veiculo: " + reserva.getVeiculo());
				contrato = requisicao.toContrato(contrato);

				// Salve o contrato no banco de dados (ou faça o que for necessário)
				this.veiculoRepository.save(reserva.getVeiculo());
				this.clienteRepository.save(cliente);
				this.contratoRepository.save(contrato);

				// Redirecione para a página do contrato recém-criado
//	            System.out.println("Lista de CNHs no contrato após toContrato: " + contrato.getCnhs());
				System.out.println("ID do contrato: " + contrato.getIdContrato());
				return new ModelAndView("redirect:/contratos/" + contrato.getIdContrato());
			} else {
				// Lógica para lidar com a reserva não encontrada
				System.out.println("Reserva não encontrada.");
				return new ModelAndView("redirect:/veiculos");
			}

		}
	}

	@GetMapping("/contratos/{idContrato}")
	public ModelAndView show(@PathVariable Integer idContrato) {

		Optional<Contrato> optional = this.contratoRepository.findById(idContrato);
		
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		if (cliente == null && funcionario == null) {
			System.out.println("Cliente nulo!");
			return new ModelAndView("redirect:/sessoes");
		}
		if (optional.isPresent()) {
			Contrato contrato = optional.get();

			ModelAndView mv = new ModelAndView("contratos/show");
			mv.addObject("contrato", contrato);
			mv.addObject("cliente", cliente);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}

	@GetMapping("/contratos/{idContrato}/edit")
	public ModelAndView edit(@PathVariable Integer idContrato, requisicaoContrato requisicao) {
		Optional<Contrato> optional = this.contratoRepository.findById(idContrato);
		if (optional.isPresent()) {
			System.out.printf("%d", idContrato);
			Contrato contrato = optional.get();
			requisicao.fromContrato(contrato);
			ModelAndView mv = new ModelAndView("contratos/edit");
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}

	
	 private final ReservaController reservaController;
	 
	   @Autowired
	    public ContratoController(ContratoRepository contratoRepository, ReservaController reservaController) {
	        this.contratoRepository = contratoRepository;
	        this.reservaController = reservaController;
	    }

	    @GetMapping("/contratos/{idContrato}/archive")
	    public String archive(@PathVariable Integer idContrato) {
	        Optional<Contrato> optional = this.contratoRepository.findById(idContrato);
	        if (optional.isPresent()) {
	            Contrato contrato = optional.get();
	            contrato.setStatus("Arquivado");
	            Reserva reserva = contrato.getReserva();
	            int idReserva = reserva.getIdReserva();
	            this.reservaController.archive(idReserva);
	            this.contratoRepository.save(contrato);
	            return "redirect:/veiculos";
	        } else {
	            System.out.println("Registro não consta no banco ou não foi encontrado.");
	            return "redirect:/contratos";
	        }
	    }
}